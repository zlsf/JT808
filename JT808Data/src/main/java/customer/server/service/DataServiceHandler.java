
package customer.server.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Utils.Constant;
import customer.server.model.PacketData;
import customer.server.model.Session;
import customer.server.model.SessionManager;
import customer.server.process.MessageProcessService;
import customer.server.process.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 消息处理.
 */
public class DataServiceHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /** 日志. */
    private final Logger log = LoggerFactory.getLogger(DataServiceHandler.class);

    /** 线程池. */
    private ExecutorService taskExecutor = Executors.newCachedThreadPool();
    /** 消息处理机. */
    private MessageProcessService messageProcessService;

    public DataServiceHandler() {
	this.messageProcessService = new MessageProcessService();
    }

    /*
     * 链接成功
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
	Session session = Session.buildSession(ctx.channel());
	SessionManager.getInstance().put(session.getId(), session);
	log.debug("建立链接: {}", session);
    }

    /*
     * 链接断开的时候
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	Session session = removeSession(Session.buildId(ctx.channel()));
	log.debug("链接断开: {}", session);
    }

    /*
     * 用户事件
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
	if (!(evt instanceof IdleStateEvent))
	    return;

	IdleStateEvent event = (IdleStateEvent) evt;

	if (event.state() == IdleState.READER_IDLE) {
	    Session session = this.removeSession(Session.buildId(ctx.channel()));
	    log.info("{} idle timeout connection: {}", event.state(), session);
	}
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf buf) throws Exception {
	log.info("channelRead0......");
	if (buf.readableBytes() <= 0)
	    return;

	byte[] bs = new byte[buf.readableBytes()];
	buf.readBytes(bs);

	taskExecutor.execute(() -> {
	    try {
		// 2进制流构建消息包
		log.info("构建包......");
		PacketData packet = PacketCodec.frameToPacket(PacketCodec.unescape(bs));
		if (packet != null) {
		    Session session = SessionManager.getInstance().findBySessionId(ctx.channel().toString());
		    messageProcessService.processPacketData(session, packet);
		}

	    } catch (Exception e) {
		log.error(e.toString());
	    }
	});
    }

    /**
     * 移除Session
     * 
     * @param sessionId
     * @return
     */
    private Session removeSession(String sessionId) {
	Session session = SessionManager.getInstance().findBySessionId(sessionId);
	if (session == null)
	    return null;

	SessionManager.getInstance().removeBySessionId(session.getId());
	return session;
    }
}
