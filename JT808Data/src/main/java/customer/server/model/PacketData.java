package customer.server.model;

import io.netty.channel.Channel;

/**
 * The Class PacketData.
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
public class PacketData {

    /** 锟斤拷息头 */
    protected MsgHeader header;

    /** 锟斤拷息锟斤拷锟絙ody锟斤拷锟斤拷 */
    protected byte[] msgBodyBytes;

    /** The checksum. */
    protected int checksum;

    /** The channel. */
    protected Channel channel;

    public PacketData() {
    }

    public PacketData(int msgId, DeviceId deviceId) {
	header = new MsgHeader(msgId, deviceId);
    }

    public int getMsgId() {
	if (this.header == null)
	    return -1;
	return header.getMsgId();
    }

    /**
     * Gets the stop id.
     *
     * @return the stop id
     */
    public DeviceId getDeviceId() {
	return header.getDeviceId();
    }

    /**
     * Gets the msg no.
     *
     * @return the msg no
     */
    public int getMsgNo() {
	return header.getMsgNo();
    }

    /**
     * Sets the header.
     *
     * @param header
     *            the new header
     */
    public void setHeader(MsgHeader header) {
	this.header = header;
    }

    /**
     * Gets the msg body.
     *
     * @return the msg body
     */
    public byte[] getMsgBody() {
	return msgBodyBytes;
    }

    /**
     * Sets the msg body.
     *
     * @param msgBodyBytes
     *            the new msg body
     */
    public void setMsgBody(byte[] msgBodyBytes) {
	this.msgBodyBytes = msgBodyBytes;
    }

    /**
     * Gets the checksum.
     *
     * @return the checksum
     */
    public int getChecksum() {
	return checksum;
    }

    /**
     * Sets the checksum.
     *
     * @param checksum
     *            the new checksum
     */
    public void setChecksum(int checksum) {
	this.checksum = checksum;
    }

    /**
     * Gets the channel.
     *
     * @return the channel
     */
    public Channel getChannel() {
	return channel;
    }

    /**
     * Sets the channel.
     *
     * @param channel
     *            the new channel
     */
    public void setChannel(Channel channel) {
	this.channel = channel;
    }

}
