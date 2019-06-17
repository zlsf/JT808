package JT809Data.netty;

import JT809Data.model.AbstractConnection;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

public class NettyChannelConnection extends AbstractConnection {

	private Channel channel;

	public NettyChannelConnection(Channel channel) {
		this.channel = channel;
	}

	@Override
	protected void send(byte[] frame) {
		channel.writeAndFlush(Unpooled.copiedBuffer(new byte[] { 0x5b }, frame, new byte[] { 0x5d }));
	}

	@Override
	public void close() {
		channel.close();
	}
}
