
package customer.server.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import customer.server.model.PackData;
import customer.server.model.Session;
import customer.server.model.SessionManager;
import customer.server.process.msg.AbstractMessage;
import customer.server.process.msg.MessageFactory;
import customer.util.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

@ChannelHandler.Sharable
public class DataServiceHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final Logger log = LoggerFactory.getLogger(DataServiceHandler.class);

    private ExecutorService taskExecutor = Executors.newCachedThreadPool();

    public DataServiceHandler() {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
	Session session = Session.buildSession(ctx.channel());
	SessionManager.getInstance().put(session.getId(), session);
	log.debug("收到连接: {}", session);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	Session session = removeSession(Session.buildId(ctx.channel()));
	log.debug("断开连接: {}", session);
    }

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
		log.info("解析包......");
		String json = new String(bs, "UTF-8");
		log.info(json);
		PackData packet = PacketCodec.convertToBaseMessageVO(json);
		if (packet != null) {
		    packet.setChannel(ctx.channel());
		    this.processPacketData(packet);
		}

	    } catch (Exception e) {
		log.error(e.toString());
	    }
	});
    }

    private void processPacketData(PackData packetData) throws Exception {
	int msgId = packetData.getMessageId();
	AbstractMessage message = MessageFactory.getInstance().getCmd(msgId, packetData);
	if (null != message) {
	    message.dealMsg();
	}
    }

    /**
     * 锟狡筹拷Session
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
