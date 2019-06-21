 package customer.server.model;

import com.kj.datacenter.msg.BaseMessage;

import io.netty.channel.Channel;

public class PackData {

	public PackData() {
		super();
	}

	public PackData(BaseMessage message, Channel channel) {
		super();
		this.message = message;
		this.channel = channel;
	}

	private BaseMessage message;

	private Channel channel;

	public BaseMessage getMessage() {
		return message;
	}

	public void setMessage(BaseMessage message) {
		this.message = message;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public int getMessageId() {
		try {
			return this.getMessage().getMagId();
		} catch (Exception ex) {
			return -1;
		}
	}

	public String getDeviceId() {
		try {
			return this.message.getDeviceId();
		} catch (Exception ex) {
			return "";
		}
	}

	public String getData() {
		return this.message.getData();
	}

}
