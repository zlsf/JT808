
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
 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�. 锟斤拷要锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷顺锟斤拷锟�
 */
public final class DataServer implements Runnable {

    /** 锟斤拷志. */
    private Logger log = LoggerFactory.getLogger(DataServer.class);
    /** 锟竭程筹拷. */
    private ExecutorService serverExecutor = Executors.newSingleThreadExecutor();

    /** The future. */
    private Future<?> future;

    /** 锟剿口猴拷. */
    private int port = 8899;

    /** 锟斤拷停锟斤拷锟�. */
    private volatile boolean running = false;

    /**
     * 锟斤拷锟叫凤拷式 D2C 锟借备锟斤拷锟斤拷锟斤拷 =0 C2C 锟斤拷锟侥讹拷锟斤拷锟斤拷=1
     */
    private static int model = Constant.Service_Model_D2C;

    /** The data service handler. */
    private DataServiceHandler dataServiceHandler;

    /** 同锟斤拷锟斤拷. */
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
     * 锟斤拷锟斤拷锟�.
     */
    public DataServer() {
	dataServiceHandler = new DataServiceHandler();
    }

    /**
     * 锟斤拷锟斤拷锟斤拷锟斤拷.
     */
    public void start() {
	log.info("锟斤拷锟斤拷锟斤拷锟斤拷......");
	synchronized (locker) {
	    this.running = true;
	    future = serverExecutor.submit(this);
	}
	log.info("锟斤拷锟斤拷锟斤拷锟斤拷 锟缴癸拷......");
    }

    /**
     * 停止锟斤拷锟斤拷.
     */
    public void stop() {
	log.info("停止 锟斤拷锟斤拷......");
	synchronized (locker) {
	    if (!running)
		return;

	    if (future == null)
		return;

	    future.cancel(running);
	    future = null;
	    this.running = false;
	}
	log.info("停止锟斤拷锟斤拷  锟缴癸拷......");
    }

    /**
     * 锟斤拷锟斤拷锟斤拷锟斤拷
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
     * 通锟斤拷锟斤拷始锟斤拷
     */
    private ChannelInitializer<SocketChannel> channelnit = new ChannelInitializer<SocketChannel>() {

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
	    channel.pipeline().addLast("logging", new LoggingHandler(LogLevel.TRACE))
		    .addLast("idle-handler", new IdleStateHandler(100, 100, 100)) // 锟斤拷锟节讹拷写锟斤拷锟斤拷锟斤拷为锟斤拷锟斤拷锟斤拷
		    .addLast("frame-decoder",
			    new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer(new byte[] { 0x7e }),
				    Unpooled.copiedBuffer(new byte[] { 0x7e, 0x7e })))
		    .addLast("808d2c-handler", dataServiceHandler);
	}
    };
}
