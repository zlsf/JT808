
package customer.server.process;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.kj.datacenter.msg.GeneralRspMsg;

import customer.server.model.Session;
import customer.server.model.SessionManager;
import customer.server.process.msg.AbstractMessage;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

public class MessageSender {

	private static final Logger log = LoggerFactory.getLogger(MessageSender.class);

	private MessageSender() {
	}

	public static MessageSender getInstance() {
		return MessageSenderInstance.INSTANCE;
	}

	private static class MessageSenderInstance {

		private static final MessageSender INSTANCE = new MessageSender();
	}

	public static void sendToDevice(List<String> deviceIDs, byte[] data) {
		if (deviceIDs == null || deviceIDs.size() <= 0)
			throw new RuntimeException("请传入设备号");
		for (String deviceId : deviceIDs) {
			try {
				Session session = SessionManager.getInstance().findByDeviceId(deviceId);
				if (session == null)
					continue;
				sendToDevice(session.getChannel(), data);
			} catch (Exception ex) {
				log.error("设备号：" + deviceId + "发送数据失败。");
				log.error(ex.getMessage());
			}
		}
	}

	/**
	 * 下发到所有设备
	 * @param data
	 */
	public static void sendToAllDevice(byte[] data) {
		Map<String, Session> sessionmap = SessionManager.getInstance().getSessionMap();
		for (String key : sessionmap.keySet()) {
			try {
				sendToDevice(sessionmap.get(key).getChannel(), data);
			} catch (Exception ex) {
				log.error(ex.getMessage());
			}
		}
	}

	/**
	 * 下发到设备
	 * 
	 * @param channel
	 * @param data
	 * @throws InterruptedException
	 */
	public static void sendToDevice(Channel channel, byte[] data) throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			ChannelFuture future = channel.writeAndFlush(Unpooled.copiedBuffer(data)).sync();
			if (future.isSuccess())
				break;
		}
	}

	/**
	 * 通用应答
	 * 
	 * @param data
	 * @throws InterruptedException
	 */
	public static void sendDownGeneralRspMsg(AbstractMessage data) throws InterruptedException {
		GeneralRspMsg msg = new GeneralRspMsg();
		msg.setDeviceId(data.getDeviceId());
		String json = JSON.toJSONString(msg);
		try {
			sendToDevice(data.getChannel(), json.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
