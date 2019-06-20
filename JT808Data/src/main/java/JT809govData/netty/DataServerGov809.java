
package JT809govData.netty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT809govData.model.GnssPlatformIdGov809;
import JT809govData.model.Session809Gov;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 主服务控制器. 主要负责启动服务端程序
 */
public final class DataServerGov809 implements Runnable {

	/** 日志. */
	private Logger log = LoggerFactory.getLogger(DataServerGov809.class);
	/** 线程池. */
	private ExecutorService serverExecutor = Executors.newSingleThreadExecutor();

	/** The future. */
	private Future<?> future;

	/** 端口号. */
	private int port = 8891;

	/** 启停标记. */
	private volatile boolean running = false;

	/** 同步锁. */
	private static Object locker = new Object();

	/**
	 * Sets the port.
	 *
	 * @param port
	 *            the new port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	public static DataServerGov809 getInstance() {
		return Singleton.singleton;
	}

	private static class Singleton {

		private static DataServerGov809 singleton = new DataServerGov809();
	}

	/**
	 * 启动服务.
	 */
	public void start() {
		log.info("启动服务......");
		synchronized (locker) {
			this.running = true;
			future = serverExecutor.submit(this);
		}
		log.info("启动服务 成功......");
	}

	/**
	 * 停止服务.
	 */
	public void stop() {
		log.info("停止 服务......");
		synchronized (locker) {
			if (!running)
				return;

			if (future == null)
				return;

			future.cancel(running);
			future = null;
			this.running = false;
		}
		log.info("停止服务  成功......");
	}

	/**
	 * 服务启动
	 */
	@Override
	public void run() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 128)
					.option(ChannelOption.TCP_NODELAY, true).childOption(ChannelOption.SO_KEEPALIVE, true)
					.childOption(ChannelOption.TCP_NODELAY, true).childHandler(this.channelnit);

			b.bind(port).sync().channel().closeFuture().sync();
		} catch (Exception e) {
			running = false;
			log.warn("EBus Stop Server stopped", e);
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	/**
	 * 通道初始化
	 */
	private ChannelInitializer<SocketChannel> channelnit = new ChannelInitializer<SocketChannel>() {

		@Override
		protected void initChannel(SocketChannel channel) throws Exception {
			channel.pipeline().addLast("logging", new LoggingHandler(LogLevel.TRACE))
					.addLast("idle-handler", new IdleStateHandler(100, 100, 100))
					.addLast("frame-decoder", new DelimiterBasedFrameDecoder(1024 * 1024 * 128,
							Unpooled.copiedBuffer(new byte[] { 0x5b }), Unpooled.copiedBuffer(new byte[] { 0x5d }),
							Unpooled.copiedBuffer(new byte[] { 0x5d, 0x5b })))
					.addLast("jt809-handler", new DataServiceHandlerGov809(localSession()));
		}
	};
	public static Session809Gov localSession() {
		return new Session809Gov(GnssPlatformIdGov809.build(1L), new byte[1]);
	}
	/**
	 * 统一发送数据
	 * 
	 * @param channel
	 * @param data
	 * @return
	 */
	public boolean sendMessageToDevice(Channel channel, byte[] data) {
		ChannelFuture future = null;
		try {
			future = channel.writeAndFlush(Unpooled.copiedBuffer(data)).sync();
			if (!future.isSuccess()) {
				return false;
			}
			return true;
		} catch (InterruptedException e) {
			log.error("发送数据异常:{}", future.cause());
			return false;
		}
	}
}
