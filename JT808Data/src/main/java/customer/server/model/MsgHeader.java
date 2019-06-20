package customer.server.model;

/**
 * The Class MsgHeader.
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
public class MsgHeader {

    /** 锟斤拷息ID */
    private int msgId;

    /** 锟斤拷息锟斤拷锟斤拷锟斤拷 */
    private int msgBodyPropsField;

    /** 锟斤拷息锟斤拷锟斤拷 */
    private int msgLength;

    /** 锟斤拷锟斤拷锟斤拷锟斤拷 */
    private int encryptionType;

    /** 锟角凤拷职锟� */
    private boolean hasSubpackage;

    /** The reserved bit. */
    private int reservedBit;

    /** 锟借备ID */
    private DeviceId deviceId;

    /** 锟斤拷息锟斤拷 */
    private int msgNo;

    /** 锟斤拷锟叫分帮拷 */
    private int totalSubpackage;

    /** 锟街帮拷锟斤拷 */
    private int subpackageNo;

    /**
     * Instantiates a new msg header.
     */
    public MsgHeader() {
    }

    /**
     * Instantiates a new msg header.
     *
     * @param msgId
     *            the msg id
     * @param stopId
     *            the stop id
     */
    public MsgHeader(int msgId, DeviceId deviceId) {
	this.msgId = msgId;
	this.deviceId = deviceId;
    }

    /**
     * Gets the msg id.
     *
     * @return the msg id
     */
    public int getMsgId() {
	return msgId;
    }

    /**
     * Sets the msg id.
     *
     * @param msgId
     *            the new msg id
     */
    public void setMsgId(int msgId) {
	this.msgId = msgId;
    }

    /**
     * Gets the msg body props field.
     *
     * @return the msg body props field
     */
    public int getMsgBodyPropsField() {
	return msgBodyPropsField;
    }

    /**
     * 锟斤拷息锟斤拷锟斤拷锟斤拷
     *
     * @param msgBodyPropsField
     *            the new msg body props field
     */
    public void setMsgBodyPropsField(int msgBodyPropsField) {
	this.msgBodyPropsField = msgBodyPropsField;
	this.msgLength = msgBodyPropsField & 0b1111111111;
	this.encryptionType = (msgBodyPropsField >>> 10) & 0b111;
	this.hasSubpackage = ((msgBodyPropsField >>> 13) & 0b1) == 1;
	this.reservedBit = (msgBodyPropsField >>> 14) & 0b11;
    }

    /**
     * 锟斤拷息锟斤拷锟斤拷
     *
     * @return the msg length
     */
    public int getMsgLength() {
	return msgLength;
    }

    /**
     * Gets the encryption type.
     *
     * @return the encryption type
     */
    public int getEncryptionType() {
	return encryptionType;
    }

    /**
     * Checks if is checks for subpackage.
     *
     * @return true, if is checks for subpackage
     */
    public boolean isHasSubpackage() {
	return hasSubpackage;
    }

    /**
     * Gets the reserved bit.
     *
     * @return the reserved bit
     */
    public int getReservedBit() {
	return reservedBit;
    }

    public DeviceId getDeviceId() {
	return deviceId;
    }

    public void setDeviceId(DeviceId deviceId) {
	this.deviceId = deviceId;
    }

    /**
     * Gets the msg no.
     *
     * @return the msg no
     */
    public int getMsgNo() {
	return msgNo;
    }

    /**
     * Sets the msg no.
     *
     * @param msgNo
     *            the new msg no
     */
    public void setMsgNo(int msgNo) {
	this.msgNo = msgNo;
    }

    /**
     * Gets the total subpackage.
     *
     * @return the total subpackage
     */
    public int getTotalSubpackage() {
	return totalSubpackage;
    }

    /**
     * Sets the total subpackage.
     *
     * @param totalSubpackage
     *            the new total subpackage
     */
    public void setTotalSubpackage(int totalSubpackage) {
	this.totalSubpackage = totalSubpackage;
    }

    /**
     * Gets the subpackage no.
     *
     * @return the subpackage no
     */
    public int getSubpackageNo() {
	return subpackageNo;
    }

    /**
     * Sets the subpackage no.
     *
     * @param subpackageNo
     *            the new subpackage no
     */
    public void setSubpackageNo(int subpackageNo) {
	this.subpackageNo = subpackageNo;
    }
}