package customer.server.process.msg;

import customer.server.model.DeviceId;
import customer.server.model.PacketData;
import io.netty.channel.Channel;

/**
 * 锟斤拷锟斤拷锟斤拷锟斤拷锟�
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

    /** 锟斤拷息ID */
    protected int msgId;

    /** 锟借备 ID */
    protected DeviceId deviceId;

    /** 锟斤拷息锟斤拷 */
    protected int msgNo;

    /** 锟斤拷息锟斤拷锟斤拷通锟斤拷 */
    protected Channel channel;
    /** 锟斤拷锟斤拷站锟斤拷锟借备锟斤拷 */
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
     * 锟斤拷锟斤拷锟斤拷息锟斤拷
     *
     * @param msgBodyBytes
     *            the new msg body
     */
    public abstract void setMsgBody(byte[] msgBodyBytes);

    /*
     * 锟斤拷取锟斤拷息锟斤拷
     */
    public abstract byte[] getMsgBody();

    /*
     * 锟斤拷取锟斤拷息ID
     */
    @Override
    public int getMsgId() {
	return msgId;
    }

    public DeviceId getDeviceId() {
	return deviceId;
    }

    /*
     * 锟斤拷取锟斤拷息锟斤拷
     */
    @Override
    public int getMsgNo() {
	return msgNo;
    }

    /*
     * 锟斤拷锟斤拷通锟斤拷
     */
    @Override
    public void setChannel(Channel channel) {
	this.channel = channel;
    }

    /*
     * 锟斤拷取通锟斤拷
     */
    @Override
    public Channel getChannel() {
	return channel;
    }

}