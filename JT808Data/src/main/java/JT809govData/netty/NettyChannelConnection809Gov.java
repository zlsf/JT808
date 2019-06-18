package JT809govData.netty;


import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

public class NettyChannelConnection809Gov extends AbstractConnection809Gov {

	private Channel channel;

	public NettyChannelConnection809Gov(Channel channel) {
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

	@Override
	public String getRemoteAddress() {
		return channel.remoteAddress().toString();
	}

	public Channel getChannel() {
		return channel;
	}
}
