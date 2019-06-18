
package customer.server.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Utils.Constant;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
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
public final class DataServer implements Runnable {

    /** 日志. */
    private Logger log = LoggerFactory.getLogger(DataServer.class);
    /** 线程池. */
    private ExecutorService serverExecutor = Executors.newSingleThreadExecutor();

    /** The future. */
    private Future<?> future;

    /** 端口号. */
    private int port = 8899;

    /** 启停标记. */
    private volatile boolean running = false;

    /**
     * 运行方式 D2C 设备对中心 =0 C2C 中心对中心=1
     */
    private static int model = Constant.Service_Model_D2C;

    /** The data service handler. */
    private DataServiceHandler dataServiceHandler;

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

    public static int getModel() {
	return DataServer.model;
    }

    public static void setModel(int model) {
	DataServer.model = model;
    }

    public static DataServer getInstance() {
	return Singleton.singleton;
    }

    private static class Singleton {

	private static DataServer singleton = new DataServer();
    }

    /**
     * 处理机.
     */
    public DataServer() {
	dataServiceHandler = new DataServiceHandler();
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
		    .childOption(ChannelOption.TCP_NODELAY, true).childHandler(channelnit);

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
		    .addLast("idle-handler", new IdleStateHandler(100, 100, 100)) // 周期读写，可认为是心跳
		    .addLast("frame-decoder",
			    new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer(new byte[] { 0x7e }),
				    Unpooled.copiedBuffer(new byte[] { 0x7e, 0x7e })))
		    .addLast("808d2c-handler", dataServiceHandler);
	}
    };
}
