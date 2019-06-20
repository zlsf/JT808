
package JT808Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT808Data.model.MessageProcessService;
import JT808Data.model.Session;
import JT808Data.model.SessionManager;
import JT808Data.model.codec.JT808Packet;
import JT808Data.model.codec.JT808PacketCodec;
import Utils.Constant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 消息处理.
 */
public class DataServiceHandler808 extends SimpleChannelInboundHandler<ByteBuf> {

	/** 日志. */
	private final Logger log = LoggerFactory.getLogger(DataServiceHandler808.class);

	/** 线程池. */
	private ExecutorService taskExecutor = Executors.newCachedThreadPool();
	/** 消息处理机. */
	private MessageProcessService messageProcessService;

	public DataServiceHandler808() {
		this.messageProcessService = new MessageProcessService();
	}

	/*
	 * 链接成功
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Session session = Session.buildSession(ctx.channel());
		session.setMessageProcessService(messageProcessService);
		SessionManager.getInstance().addSession(session.getId(), session);
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
				// if (DataServer.getModel() == Constant.Service_Model_C2C) { }
				// 2进制流构建消息包
				log.info("构建包......");
				if (DataServer808.getModel() == Constant.Service_Model_D2C) {
					JT808Packet packet = JT808PacketCodec.frameToPacket(JT808PacketCodec.unescape(bs));
					if (packet != null) {
						Session session = SessionManager.getInstance().getSession(Session.buildId(ctx.channel()));
						session.processPacket(packet);
					}
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
