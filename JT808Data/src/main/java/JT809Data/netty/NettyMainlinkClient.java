package JT809Data.netty;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT809Data.model.JT809Constant;
import JT809Data.model.JT809Packet;
import JT809Data.model.Session809;
import JT809Data.netty.msghandler.UpConnectReq;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyMainlinkClient implements MainlinkClient {
	private static final Logger log = LoggerFactory.getLogger(NettyMainlinkClient.class);

	private Session809 session;
	private JT809PacketProcessor packetProcessor;
	private JT809ConnectionManager connectionManager;

	private EventLoopGroup group;
	private NettyChannelConnection connection;

	private int idleTimeout;
	private String host;
	private int port;

	private long userId;
	private String password;
	private String downlinkHost;
	private int downlinkPort;

	@Override
	public void connect(String host, int port) {
		this.host = host;
		this.port = port;
		doConnectInternal();
	}

	private void doConnectInternal() {
		this.group = new NioEventLoopGroup();
		CountDownLatch latch = new CountDownLatch(1);
		doConnect(latch);
		try {
			latch.await();
		} catch (InterruptedException e) {
		}
	}

	private void doConnect(CountDownLatch latch) {
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(group).remoteAddress(host, port)
				.channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel channel) throws Exception {
						channel.pipeline().addLast("logging", new LoggingHandler(LogLevel.TRACE))
								.addLast("idle-handler", new IdleStateHandler(0, idleTimeout, 0))
								.addLast("frame-decoder", new DelimiterBasedFrameDecoder(1024 * 1024 * 128, Unpooled.copiedBuffer(new byte[] { 0x5b }), Unpooled.copiedBuffer(new byte[] { 0x5d }), Unpooled.copiedBuffer(new byte[] { 0x5d, 0x5b })))
								.addLast("jt809-handler", new ClientHandler());
					}
				});
		bootstrap.connect().addListener(new ChannelFutureListener() {

			@Override
			public void operationComplete(ChannelFuture f) throws Exception {
				if (!f.isSuccess()) {
					f.channel().eventLoop().schedule(new Runnable() {

						@Override
						public void run() {
							doConnect(latch);
						}
					}, idleTimeout / 2, TimeUnit.SECONDS);
				} else {
					connection = new NettyChannelConnection(f.channel());
					connection.setSession(session);
					latch.countDown();
				}
			}
		});
	}

	@Override
	public void setSession(Session809 session) {
		this.session = session;
	}

	@Override
	public void setPacketProcessor(JT809PacketProcessor processor) {
		this.packetProcessor = processor;
	}

	@Override
	public void setConnectionManager(JT809ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	@Override
	public void setIdleTimeout(int timeout) {
		this.idleTimeout = timeout;
	}

	@Override
	public boolean isConnected() {
		return connection != null;
	}

	@Override
	public void login(long userId, String password, String downlinkHost, int downlinkPort) {
		this.userId = userId;
		this.password = password;
		this.downlinkHost = downlinkHost;
		this.downlinkPort = downlinkPort;
		doLoginInternal();
	}

	private void doLoginInternal() {
		UpConnectReq req = new UpConnectReq();
		req.setUserId(userId);
		req.setPassword(password);
		req.setDownLinkHost(downlinkHost);
		req.setDownLinkPort(downlinkPort);
		connection.sendMessage(req);
	}

	@Override
	public void close() {
		group.shutdownGracefully();
	}

	private class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

		@Override
		protected void channelRead0(ChannelHandlerContext arg0, ByteBuf buf) throws Exception {
			if (buf.readableBytes() <= 0) {
				return;
			}
			byte[] bs = new byte[buf.readableBytes()];
			buf.readBytes(bs);
			JT809Packet packet = session.getPacketDeser().deserialize(bs);
			if (connection.isAuthenticated() || JT809Constant.UP_CONNECT_RSP == packet.getMsgId()) {
				packetProcessor.processPacket(connection, packet);
			} else {
				log.info("unauthenticated connection, ignore received packet");
			}
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			// log
		}

		@Override
		public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			connectionManager.setDownlinkConnection(null);
			ctx.channel().eventLoop().execute(new Runnable() {

				@Override
				public void run() {
					doConnectInternal();
					if (isConnected()) {
						doLoginInternal();
					}
				}
			});
		}

		@Override
		public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
			if (evt instanceof IdleStateEvent) {
				IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
				if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
					UpLinkTestReq req = new UpLinkTestReq();
					connection.sendMessage(req);
				}
			}
		}
	}

}
