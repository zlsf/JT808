package customer.server.process.msg;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Utils.ByteArrayUtil;
import customer.server.model.DeviceId;
import customer.server.model.MsgHeader;
import customer.server.model.PacketData;

/**
 * 锟斤拷息锟斤拷锟斤拷
 *
 * @author
 * 
 *         <p>
 * 	Modification History:
 *         </p>
 *         <p>
 * 	Date Author Description
 *         </p>
 *         <p>
 * 	------------------------------------------------------------------
 *         </p>
 *         <p>
 *         </p>
 *         <p>
 *         </p>
 */

public class MessageDecoder {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(MessageDecoder.class);

    /**
     * 锟斤拷转锟藉处锟斤拷.
     *
     * @param frame
     *            the frame
     * @return the byte[]
     */
    private byte[] decodeEscape(byte[] frame) {
	ByteArrayOutputStream out = new ByteArrayOutputStream(frame.length);
	for (int i = 0; i < frame.length; i++) {
	    byte b = frame[i];
	    if (i < frame.length - 1) {
		byte c = frame[i + 1];
		if (b == 0x7D && c == 0x01) {
		    out.write(0x7E);
		    i++;
		} else if (b == 0x7D && c == 0x02) {
		    out.write(0x7D);
		    i++;
		} else {
		    out.write(b);
		}
	    } else {
		out.write(b);
	    }
	}

	byte[] decodeFrame = out.toByteArray();
	return decodeFrame;
    }

    /**
     * 锟街节凤拷锟�
     *
     * @param data
     *            the data
     * @return the packet data
     */
    public PacketData bytes2PacketData(byte[] data) {
	data = decodeEscape(data);
	try {
	    PacketData pd = new PacketData();
	    MsgHeader header = parseMsgHeaderFromBytes(data);
	    pd.setHeader(header);
	    int msgBodyByteStartIndex = 12;
	    if (header.isHasSubpackage()) {
		msgBodyByteStartIndex = 16;
	    }
	    byte[] bodyBytes = Arrays.copyOfRange(data, msgBodyByteStartIndex, data.length - 1);
	    pd.setMsgBody(bodyBytes);
	    pd.setChecksum(data[data.length - 1] & 0xff);

	    // 校锟斤拷
	    if (header.getMsgLength() != data.length) {
		log.warn("锟斤拷息锟斤拷锟斤拷锟斤拷实锟绞筹拷锟饺诧拷锟斤拷{}!={}", header.getMsgLength(), data.length);
	    }
	    int calChecksum = calculateChecksum(data, 0, data.length - 1);
	    if (calChecksum != pd.getChecksum()) {
		log.warn("锟斤拷息校锟斤拷锟斤拷锟斤拷实锟绞硷拷锟斤拷值{}!={}", pd.getChecksum(), calChecksum);
	    }
	    return pd;
	} catch (Exception e) {
	    return null;
	}
    }

    /**
     * 锟斤拷装锟斤拷息头
     *
     * @param data
     *            the data
     * @return the msg header
     */
    public MsgHeader parseMsgHeaderFromBytes(byte[] data) {
	MsgHeader header = new MsgHeader();
	header.setMsgId(ByteArrayUtil.getUnsignedShort(data, 0));
	header.setMsgBodyPropsField(ByteArrayUtil.getUnsignedShort(data, 2));
	header.setDeviceId(new DeviceId(ByteArrayUtil.copyOfRange(data, 4, 10)));
	header.setMsgNo(ByteArrayUtil.getUnsignedShort(data, 10));
	if (header.isHasSubpackage()) {
	    header.setTotalSubpackage(ByteArrayUtil.getUnsignedShort(data, 12));
	    header.setSubpackageNo(ByteArrayUtil.getUnsignedShort(data, 14));
	}
	return header;
    }

    /**
     * Calculate checksum.
     *
     * @param data
     *            the data
     * @param from
     *            the from
     * @param to
     *            the to
     * @return the int
     */
    public static int calculateChecksum(byte[] data, int from, int to) {
	int cs = 0;
	for (int i = from; i < to; i++) {
	    cs ^= data[i];
	}
	return cs & 0xff;
    }
}
