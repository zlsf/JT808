package customer.server.process.msg;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

import com.kj.datacenter.core.ProtocolConstant;

import Utils.ByteArrayUtil;
import customer.server.model.DeviceId;

/**
 * 锟斤拷息锟斤拷锟斤拷
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

public class MessageEncoder {

    /**
     * 转锟藉处锟斤拷.
     *
     * @param frame
     *            the frame
     * @return the byte[]
     */
    private byte[] escape(byte[] frame) {
	ByteArrayOutputStream out = new ByteArrayOutputStream(frame.length);
	for (int i = 0; i < frame.length; i++) {
	    byte b = frame[i];
	    if (b == 0x7E) {
		out.write(0x7D);
		out.write(0x01);
	    } else if (b == 0x7D) {
		out.write(0x7D);
		out.write(0x02);
	    } else {
		out.write(b);
	    }
	}
	byte[] escapeFrame = out.toByteArray();
	return escapeFrame;
    }

    /**
     * 锟斤拷锟斤拷锟斤拷息
     *
     * @param rsp
     *            the rsp
     * @param msgNo
     *            the msg no
     * @return the byte[]
     */
    public byte[] encodeMessage(SentMessage rsp, int msgNo) {
	byte[] msgBody = rsp.getMsgBody();
	int msgBodyPropsField = generateMsgBodyPropsField(msgBody.length, 0b000, false, 0b00);
	byte[] msgHeader = generateMsgHeader(rsp.getMsgId(), msgBodyPropsField, rsp.getDeviceId(), msgNo);
	byte[] headerAndBody = ByteArrayUtil.concact(msgHeader, msgBody);
	int checksum = MessageDecoder.calculateChecksum(headerAndBody, 0, headerAndBody.length);
	byte[] escapedMsg = escape(ByteArrayUtil.concact(headerAndBody, new byte[] { (byte) checksum }));
	return ByteArrayUtil.concact(new byte[] { ProtocolConstant.FRAME_DELIMITER }, escapedMsg,
		new byte[] { ProtocolConstant.FRAME_DELIMITER });
    }

    /**
     * 锟斤拷锟斤拷锟较拷锟斤拷锟斤拷锟�
     *
     * @param bodyLength
     *            the body length
     * @param encryptionType
     *            the encryption type
     * @param hasSubpackage
     *            the has subpackage
     * @param reservedbits
     *            the reservedbits
     * @return the int
     */
    public int generateMsgBodyPropsField(int bodyLength, int encryptionType, boolean hasSubpackage, int reservedbits) {
	return ((bodyLength + (hasSubpackage ? 16 : 12) + 1) & 0b1111111111) | (encryptionType & 0b111) << 10
		| (hasSubpackage ? 1 << 13 : 0) | (reservedbits & 0b11) << 14;
    }

    /**
     * 锟斤拷锟斤拷锟斤拷息头
     *
     * @param msgId
     *            the msg id
     * @param msgBodyPropsField
     *            the msg body props field
     * @param stopId
     *            the stop id
     * @param msgNo
     *            the msg no
     * @return the byte[]
     */
    public byte[] generateMsgHeader(int msgId, int msgBodyPropsField, DeviceId deviceId, int msgNo) {
	return generateMsgHeader(msgId, msgBodyPropsField, deviceId, msgNo, false, 0, 0);
    }

    /**
     * 锟斤拷锟斤拷锟斤拷息头
     *
     * @param msgId
     *            the msg id
     * @param msgBodyPropsField
     *            the msg body props field
     * @param stopId
     *            the stop id
     * @param msgNo
     *            the msg no
     * @param hasSubpackage
     *            the has subpackage
     * @param totalSubpackage
     *            the total subpackage
     * @param subpackageNo
     *            the subpackage no
     * @return the byte[]
     */
    public byte[] generateMsgHeader(int msgId, int msgBodyPropsField, DeviceId deviceId, int msgNo,
	    boolean hasSubpackage, int totalSubpackage, int subpackageNo) {
	int headerLength = 12;
	if (hasSubpackage) {
	    headerLength = 16;
	}
	ByteBuffer buffer = ByteBuffer.allocate(headerLength);
	buffer.putShort((short) msgId);
	buffer.putShort((short) msgBodyPropsField);
	buffer.put(deviceId.toByteArray());
	buffer.putShort((short) msgNo);
	if (hasSubpackage) {
	    buffer.putShort((short) totalSubpackage);
	    buffer.putShort((short) subpackageNo);
	}
	return buffer.array();
    }
}
