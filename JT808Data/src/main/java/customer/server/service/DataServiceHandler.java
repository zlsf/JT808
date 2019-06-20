
package customer.server.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import customer.server.model.PacketData;
import customer.server.model.Session;
import customer.server.model.SessionManager;
import customer.server.process.PacketCodec;
import customer.server.process.msg.AbstractMessage;
import customer.server.process.msg.MessageFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 锟斤拷息锟斤拷锟斤拷.
 */
public class DataServiceHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /** 锟斤拷志. */
    private final Logger log = LoggerFactory.getLogger(DataServiceHandler.class);

    /** 锟竭程筹拷. */
    private ExecutorService taskExecutor = Executors.newCachedThreadPool();

    public DataServiceHandler() {

    }

    /*
     * 锟斤拷锟接成癸拷
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
	Session session = Session.buildSession(ctx.channel());
	SessionManager.getInstance().put(session.getId(), session);
	log.debug("锟斤拷锟斤拷锟斤拷锟斤拷: {}", session);
    }

    /*
     * 锟斤拷锟接断匡拷锟斤拷时锟斤拷
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	Session session = removeSession(Session.buildId(ctx.channel()));
	log.debug("锟斤拷锟接断匡拷: {}", session);
    }

    /*
     * 锟矫伙拷锟铰硷拷
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
		// 2锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷息锟斤拷
		log.info("锟斤拷锟斤拷锟斤拷......");
		PacketData packet = PacketCodec.frameToPacket(PacketCodec.unescape(bs));
		packet.setChannel(ctx.channel());
		if (packet != null) {
		    this.processPacketData(packet);
		}

	    } catch (Exception e) {
		log.error(e.toString());
	    }
	});
    }

    private void processPacketData(PacketData packetData) throws Exception {
	int msgId = packetData.getMsgId();
	AbstractMessage message = MessageFactory.getInstance().getCmd(msgId, packetData);
	if (null != message) {
	    message.setDeviceCode(packetData.getDeviceId().getId());
	    message.dealMessage();
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
