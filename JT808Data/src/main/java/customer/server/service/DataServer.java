
package customer.server.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

public final class DataServer implements Runnable {

    private Logger log = LoggerFactory.getLogger(DataServer.class);

    private ExecutorService serverExecutor = Executors.newSingleThreadExecutor();

    /** The future. */
    private Future<?> future;

    private int port = 8899;

    private volatile boolean running = false;

    private DataServiceHandler dataServiceHandler;

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

    public DataServer() {
	dataServiceHandler = new DataServiceHandler();
    }

    public void start() {
	log.info("开启服务器......");
	synchronized (locker) {
	    this.running = true;
	    future = serverExecutor.submit(this);
	}
	log.info("开启服务结束......");
    }

    /**
     * 停止锟斤拷锟斤拷.
     */
    public void stop() {
	log.info("停止服务......");
	synchronized (locker) {
	    if (!running)
		return;

	    if (future == null)
		return;

	    future.cancel(running);
	    future = null;
	    this.running = false;
	}
	log.info("停止服务结束......");
    }

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
	    log.warn("开启Netty服务异常", e);
	} finally {
	    bossGroup.shutdownGracefully();
	    workerGroup.shutdownGracefully();
	}
    }

    private ChannelInitializer<SocketChannel> channelnit = new ChannelInitializer<SocketChannel>() {

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
	    channel.pipeline().addLast("logging", new LoggingHandler(LogLevel.TRACE))
		    .addLast("idle-handler", new IdleStateHandler(100, 100, 100))
		    .addLast("service-handler", dataServiceHandler);
	}
    };
}
