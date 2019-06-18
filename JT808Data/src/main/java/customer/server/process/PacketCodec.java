
package customer.server.process;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Utils.ByteArrayUtil;
import customer.server.model.DeviceId;
import customer.server.model.MsgHeader;
import customer.server.model.PacketData;

public class PacketCodec {

    private static final Logger log = LoggerFactory.getLogger(PacketCodec.class);

    /**
     * 反转义处理
     * 
     * @param frame
     * @return
     */
    public static byte[] unescape(byte[] frame) {
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
     * 帧包 转换
     * 
     * @param data
     * @return
     */
    public static PacketData frameToPacket(byte[] data) {
	data = unescape(data);
	try {
	    PacketData pd = new PacketData();
	    MsgHeader header = parseHeaderFromBytes(data);
	    pd.setHeader(header);
	    int msgBodyByteStartIndex = 12;
	    if (header.isHasSubpackage()) {
		msgBodyByteStartIndex = 16;
	    }
	    byte[] bodyBytes = Arrays.copyOfRange(data, msgBodyByteStartIndex, data.length - 1);
	    pd.setMsgBody(bodyBytes);
	    pd.setChecksum(data[data.length - 1] & 0xff);

	    // 校验
	    if (header.getMsgLength() != data.length) {
		log.warn("消息长度与实际长度不符{}!={}", header.getMsgLength(), data.length);
	    }
	    int calChecksum = calculateChecksum(data, 0, data.length - 1);
	    if (calChecksum != pd.getChecksum()) {
		log.warn("消息校验码与实际计算值{}!={}", pd.getChecksum(), calChecksum);
	    }
	    return pd;
	} catch (Exception e) {
	    return null;
	}
    }

    /**
     * 构造头部
     * 
     * @param data
     * @return
     */
    private static MsgHeader parseHeaderFromBytes(byte[] data) {
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
     * 校验码
     * 
     * @param data
     * @param from
     * @param to
     * @return
     */
    public static int calculateChecksum(byte[] data, int from, int to) {
	int cs = 0;
	for (int i = from; i < to; i++) {
	    cs ^= data[i];
	}
	return cs & 0xff;
    }
}
