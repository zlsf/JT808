package customer.server.process.msg;

import customer.server.model.DeviceId;
import customer.server.model.PacketData;
import io.netty.channel.Channel;

/**
 * 命令抽象类
 *
 * @author
 * 
 *         <p>
 *         Modification History:
 *         </p>
 *         <p>
 *         Date Author Description
 *         </p>
 *         <p>
 *         ------------------------------------------------------------------
 *         </p>
 *         <p>
 *         </p>
 *         <p>
 *         </p>
 */
public abstract class AbstractMessage implements ReceivedMessage, SentMessage {

    /** 消息ID */
    protected int msgId;

    /** 设备 ID */
    protected DeviceId deviceId;

    /** 消息号 */
    protected int msgNo;

    /** 消息所属通道 */
    protected Channel channel;
    /** 电子站牌设备号 */
    protected String deviceCode;

    public String getDeviceCode() {
	return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
	this.deviceCode = deviceCode;
    }

    public AbstractMessage(int msgId, DeviceId deviceId) {
	this.msgId = msgId;
	this.deviceId = deviceId;
    }

    public AbstractMessage(PacketData data) {
	if (data == null)
	    return;
	this.msgId = data.getMsgId();
	this.deviceId = data.getDeviceId();
	this.msgNo = data.getMsgNo();
	this.channel = data.getChannel();
	try {
	    setMsgBody(data.getMsgBody());
	} catch (Exception ex) {
	    return;
	}
    }

    @Override
    public void dealMessage() {
    }

    /**
     * 设置消息体
     *
     * @param msgBodyBytes
     *            the new msg body
     */
    public abstract void setMsgBody(byte[] msgBodyBytes);

    /*
     * 获取消息体
     */
    public abstract byte[] getMsgBody();

    /*
     * 获取消息ID
     */
    @Override
    public int getMsgId() {
	return msgId;
    }

    public DeviceId getDeviceId() {
	return deviceId;
    }

    /*
     * 获取消息号
     */
    @Override
    public int getMsgNo() {
	return msgNo;
    }

    /*
     * 设置通道
     */
    @Override
    public void setChannel(Channel channel) {
	this.channel = channel;
    }

    /*
     * 获取通道
     */
    @Override
    public Channel getChannel() {
	return channel;
    }

}