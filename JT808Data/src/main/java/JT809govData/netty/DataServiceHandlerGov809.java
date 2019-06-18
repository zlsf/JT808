
package JT809govData.netty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT809Data.model.JT809Constant;
import JT809govData.model.JT809ConnectionManagerGov;
import JT809govData.model.JT809GovPacket;
import JT809govData.model.Session809Gov;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

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

	private NettyChannelConnection809Gov connection;

	private Session809Gov session;

	private JT809ConnectionManagerGov connectionManager;

	public DataServiceHandlerGov809() {
		this.messageProcessService = new MessageProcessService();
	}

	public DataServiceHandlerGov809(Session809Gov session) {
		this.messageProcessService = new MessageProcessService();
		this.session = session;
	}

	public void setSession(Session809Gov session) {
		this.session = session;
	}

	public void setConnectionManager(JT809ConnectionManagerGov connectionManager) {
		this.connectionManager = connectionManager;
	}

	/*
	 * ���ӳɹ�
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.connection = new NettyChannelConnection809Gov(ctx.channel());
		connection.setSession(session);
		log.debug("��������: {}", session);
	}

	/*
	 * ���ӶϿ���ʱ��
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		connectionManager.removeMainlinkConnection(connection);
		log.debug("���ӶϿ�: {}", session);
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
				JT809GovPacket packet = session.getPacketDeser().deserialize(bs);
				if (connection.isAuthenticated() || JT809Constant.UP_CONNECT_REQ == packet.getMsgId()) {
					// packetProcessor.processPacket(connection, packet);
				} else {
					log.info("unauthenticated main link connection, ignore packet of 0x{} from {}",
							Integer.toHexString(packet.getMsgId()), packet.getGnssPlatformId());
				}
			} catch (Exception e) {
				log.error(e.toString());
			}
		});
	}

}
