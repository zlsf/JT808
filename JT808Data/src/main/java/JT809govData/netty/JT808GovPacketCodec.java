
package JT809govData.netty;

import io.netty.buffer.ByteBufUtil;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT808Data.model.codec.JT808Packet.Header;
import JT809govData.model.JT809GovPacket;
import JT809govData.model.MsgHeader809Gov;
import Utils.ByteArrayUtil;
import Utils.JT808Constant;

public class JT808GovPacketCodec {

    private static final Logger log = LoggerFactory.getLogger(JT808GovPacketCodec.class);

    /**
     * 转义处理
     * 
     * @param frame
     * @return
     */
    public static byte[] escape(byte[] frame) {
	log.trace("original message: {}", ByteBufUtil.hexDump(frame));
	ByteArrayOutputStream out = new ByteArrayOutputStream(frame.length);
	for (int i = 0; i < frame.length; i++) {
	    byte b = frame[i];
	    if (b == 0x5A) {
		out.write(0x5A);
		out.write(0x02);
	    } else if (b == 0x5B) {
		out.write(0x5A);
		out.write(0x01);
	    } else if (b == 0x5D) {
		out.write(0x5E);
		out.write(0x01);
	    } else if (b == 0x5E) {
		out.write(0x5E);
		out.write(0x02);
	    } else {
		out.write(b);
	    }
	}
	return out.toByteArray();
    }

    /**
     * 反转义处理
     * 
     * @param frame
     * @return
     */
    public static byte[] unescape(byte[] frame) {
	log.trace("escaped message: {}", ByteBufUtil.hexDump(frame));
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	for (int i = 0; i < frame.length; i++) {
	    byte b = frame[i];
	    if (i < frame.length - 1) {
		byte c = frame[i + 1];
		if (b == 0x5A && c == 0x01) {
		    out.write(0x5B);
		    i++;
		} else if (b == 0x5A && c == 0x02) {
		    out.write(0x5A);
		    i++;
		} else if (b == 0x5E && c == 0x01) {
		    out.write(0x5D);
		    i++;
		} else if (b == 0x5E && c == 0x02) {
		    out.write(0x5E);
		    i++;
		} else {
		    out.write(b);
		}
	    } else {
		out.write(b);
	    }
	}
	return out.toByteArray();
    }

    /**
     * 帧包 转换
     * 
     * @param data
     * @return
     */
    public static JT809GovPacket frameToPacket(byte[] data) {
	try {
	    JT809GovPacket pd = new JT809GovPacket();
	    Header header = parseHeaderFromBytes(data);
	    pd.setHeader(header);
	    int msgBodyByteStartIndex = 12;
	    if (header.isSubpacket()) {
		msgBodyByteStartIndex = 16;
	    }
	    byte[] bodyBytes = Arrays.copyOfRange(data, msgBodyByteStartIndex, data.length - 1);
	    pd.setMsgBodyBytes(bodyBytes);
	    pd.setChecksum(data[data.length - 1] & 0xff);

	    // 校验
	    if (header.getMsgBodyLength() != bodyBytes.length) {
		log.warn("消息长度与实际长度不符{}!={}", header.getMsgBodyLength(), bodyBytes.length);
	    }
	    int calChecksum = calculateChecksum(data, 0, data.length - 1);
	    if (calChecksum != pd.getChecksum()) {
		log.warn("消息校验码与实际计算值{}!={}", pd.getChecksum(), calChecksum);
	    }
	    return pd;
	} catch (Exception e) {
	    log.warn("创建包错误", e);
	    return null;
	}
    }

    /**
     * 构造头部
     * 
     * @param data
     * @return
     */
    private static MsgHeader809Gov parseHeaderFromBytes(byte[] data) {
	MsgHeader809Gov header = new MsgHeader809Gov();
	header.setMsgId(ByteArrayUtil.getUnsignedShort(data, 0));
	header.setMsgBodyPropsField(ByteArrayUtil.getUnsignedShort(data, 2));
	header.setTerminalId(new TerminalId(ByteArrayUtil.copyOfRange(data, 4, 10)));
	header.setMsgNo(ByteArrayUtil.getUnsignedShort(data, 10));
	if (header.isSubpacket()) {
	    header.setSubpacketCount(ByteArrayUtil.getUnsignedShort(data, 12));
	    header.setSubpacketNo(ByteArrayUtil.getUnsignedShort(data, 14));
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

    /**
     * 消息编码
     * 
     * @param lmsg
     * @return
     */
    public static List<JT808Packet> encodeMessage(OutboundMessage lmsg) {
	byte[] msgBody = lmsg.getMsgBody();
	if (msgBody.length <= 0) {
	    JT808Packet pd = new JT808Packet();
	    List<JT808Packet> result = new ArrayList<>(1);
	    Header header = new Header(lmsg.getMsgId(), lmsg.getTerminalId());
	    header.setEncryptionType(0b000);
	    header.setSubpacket(false);
	    header.setReservedBit(0b00);
	    header.setSubpacketCount(0);
	    header.setSubpacketNo(1);
	    pd.setHeader(header);
	    pd.setMsgBodyBytes(new byte[0]);
	    result.add(pd);
	    return result;
	}

	int size = msgBody.length / JT808Constant.MAX_MSG_BODY_LENGTH
		+ msgBody.length % JT808Constant.MAX_MSG_BODY_LENGTH > 0 ? 1 : 0;
	List<JT808Packet> result = new ArrayList<>(size);
	for (int i = 0; i < size; i++) {
	    JT808Packet pd = new JT808Packet();
	    pd.setMsgBodyBytes(ByteArrayUtil.copyOfRange(msgBody, JT808Constant.MAX_MSG_BODY_LENGTH * i,
		    Math.min(msgBody.length, JT808Constant.MAX_MSG_BODY_LENGTH * i + 1)));
	    Header header = new Header(lmsg.getMsgId(), lmsg.getTerminalId());
	    header.setMsgBodyLength(pd.getMsgBodyBytes().length);
	    header.setEncryptionType(0b000);
	    header.setSubpacket(true);
	    header.setReservedBit(0b00);
	    header.setSubpacketCount(size);
	    header.setSubpacketNo(i + 1);
	    pd.setHeader(header);
	    result.add(pd);
	}
	return result;
    }
}
