package customer.server.process;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.kj.datacenter.msg.GeneralRspMsg;

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

    /**
     * 锟斤拷锟酵碉拷锟借备
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
