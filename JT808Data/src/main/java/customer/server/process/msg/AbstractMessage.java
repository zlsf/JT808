
package customer.server.process.msg;

import customer.server.model.PackData;
import io.netty.channel.Channel;

public abstract class AbstractMessage {

	protected PackData data;

	public AbstractMessage(PackData data) {
		this.data = data;
	}

	public String getDeviceId() {
		try {
			return this.data.getDeviceId();
		} catch (Exception ex) {
			return "";
		}
	}

	public abstract void dealMsg();


	public Channel getChannel() {
		try {
			return this.data.getChannel();
		} catch (Exception ex) {
			return null;
		}
	}
}
