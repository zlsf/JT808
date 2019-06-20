package customer.server.process.msg;

import customer.server.model.DeviceId;
import customer.server.model.PacketData;
import io.netty.channel.Channel;

/**
 * ���������
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

    /** ��ϢID */
    protected int msgId;

    /** �豸 ID */
    protected DeviceId deviceId;

    /** ��Ϣ�� */
    protected int msgNo;

    /** ��Ϣ����ͨ�� */
    protected Channel channel;
    /** ����վ���豸�� */
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
     * ������Ϣ��
     *
     * @param msgBodyBytes
     *            the new msg body
     */
    public abstract void setMsgBody(byte[] msgBodyBytes);

    /*
     * ��ȡ��Ϣ��
     */
    public abstract byte[] getMsgBody();

    /*
     * ��ȡ��ϢID
     */
    @Override
    public int getMsgId() {
	return msgId;
    }

    public DeviceId getDeviceId() {
	return deviceId;
    }

    /*
     * ��ȡ��Ϣ��
     */
    @Override
    public int getMsgNo() {
	return msgNo;
    }

    /*
     * ����ͨ��
     */
    @Override
    public void setChannel(Channel channel) {
	this.channel = channel;
    }

    /*
     * ��ȡͨ��
     */
    @Override
    public Channel getChannel() {
	return channel;
    }

}