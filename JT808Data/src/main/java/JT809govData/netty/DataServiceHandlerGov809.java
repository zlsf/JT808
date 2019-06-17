
package JT809govData.netty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT809govData.model.JT809GovPacket;
import Utils.Constant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * ��Ϣ����.
 */
public class DataServiceHandlerGov809 extends SimpleChannelInboundHandler<ByteBuf> {

    /** ��־. */
    private final Logger log = LoggerFactory.getLogger(DataServiceHandlerGov809.class);

    /** �̳߳�. */
    private ExecutorService taskExecutor = Executors.newCachedThreadPool();
    /** ��Ϣ�����. */
    private MessageProcessService messageProcessService;

    public DataServiceHandlerGov809() {
	this.messageProcessService = new MessageProcessService();
    }

    /*
     * ���ӳɹ�
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
	Session session = Session.buildSession(ctx.channel());
	session.setMessageProcessService(messageProcessService);
	SessionManager.getInstance().addSession(session.getId(), session);
	log.debug("��������: {}", session);
    }

    /*
     * ���ӶϿ���ʱ��
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	Session session = removeSession(Session.buildId(ctx.channel()));
	log.debug("���ӶϿ�: {}", session);
    }

    /*
     * �û��¼�
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
		// if (DataServer.getModel() == Constant.Service_Model_C2C) { }
		// 2������������Ϣ��
		log.info("������......");

		JT809GovPacket packet = JT808PacketCodec.frameToPacket(JT808PacketCodec.unescape(bs));
		if (packet != null) {
		    Session session = SessionManager.getInstance().getSession(Session.buildId(ctx.channel()));
		    session.processPacket(packet);

		}
	    } catch (Exception e) {
		log.error(e.toString());
	    }
	});
    }

    /**
     * �Ƴ�Session
     * 
     * @param sessionId
     * @return
     */
    private Session removeSession(String sessionId) {
	Session session = SessionManager.getInstance().getSession(sessionId);
	if (session == null)
	    return null;
	if (session.isAuthenticated()) {
	    SessionManager.getInstance().removeTerminal(session.getTerminalId(), session.getId());
	} else {
	    SessionManager.getInstance().removeSession(session.getId());
	}
	return session;
    }
}
