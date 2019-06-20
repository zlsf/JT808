
package JT809govData.model;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Utils.ByteArrayUtil;

public class JT809PacketDeserGov {

	private static final Logger log = LoggerFactory.getLogger(JT809PacketDeserGov.class);

	private long m1;
	private long ia1;
	private long ic1;

	public JT809PacketDeserGov(long m1, long ia1, long ic1) {
		this.m1 = m1;
		this.ia1 = ia1;
		this.ic1 = ic1;
	}

	public byte[] serialize(JT809GovPacket packet) {
		byte[] headerBytes = serializeHeader(packet.getHeader());
		byte[] msgBodyBytes = packet.getMsgBody();
		if (packet.getHeader().isEncrypt()) {
			msgBodyBytes = encrypt(packet.getHeader().getEncryptKey(), msgBodyBytes);
		}
		byte[] headerAndBodyBytes = ByteArrayUtil.concact(headerBytes, msgBodyBytes);
		int crcCode = crcCcitt(headerAndBodyBytes);
		byte[] frame = ByteArrayUtil.concact(headerAndBodyBytes, new byte[] { (byte) (crcCode >> 8), (byte) crcCode });
		if (log.isTraceEnabled()) {
			log.trace("unescaped message: {}", ByteArrayUtil.hexDump(frame));
		}
		byte[] escapedFrame = escape(frame);
		if (log.isTraceEnabled()) {
			log.trace("send escaped message: {}", ByteArrayUtil.hexDump(escapedFrame));
		}
		return escapedFrame;
	}

	/**
	 * 解析
	 * 
	 * @param frame
	 * @return
	 */
	public JT809GovPacket deserialize(byte[] escapedFrame) {
		if (log.isTraceEnabled()) {
			log.trace("received escaped message: {}", ByteArrayUtil.hexDump(escapedFrame));
		}
		byte[] frame = unescape(escapedFrame);
		if (log.isTraceEnabled()) {
			log.trace("unescaped message: {}", ByteArrayUtil.hexDump(frame));
		}
		int crcCode = ByteArrayUtil.getUnsignedShort(frame, frame.length - 2);
		int calcCrc = crcCcitt(ByteArrayUtil.copyOfRange(frame, 0, frame.length - 2));
		MsgHeader809Gov header = deserializeHeader(ByteArrayUtil.copyOfRange(frame, 0, 22));
		if (crcCode != calcCrc) {
			log.warn("the CRC_CODE field value 0x{} is not equals to the computed crc value 0x{} of 0x{} from {}",
					Integer.toHexString(crcCode), Integer.toHexString(calcCrc), Integer.toHexString(header.getMsgId()),
					header.getGnssPlatformId());
		}
		if (header.getMsgLength() != (frame.length + 2)) {
			log.warn("the MSG_LENGTH field value 0x{} is not equals to the fact msg length 0x{} of 0x{} from {}",
					Long.toHexString(header.getMsgLength()), Integer.toHexString(frame.length + 2),
					Integer.toHexString(header.getMsgId()), header.getGnssPlatformId());
			log.warn("the unescaped message: {}", ByteArrayUtil.hexDump(frame));
		}
		byte[] msgBodyBytes = ByteArrayUtil.copyOfRange(frame, 22, frame.length - 2);
		if (header.isEncrypt()) {
			msgBodyBytes = encrypt(header.getEncryptKey(), msgBodyBytes);
		}

		JT809GovPacket packet = new JT809GovPacket(header, msgBodyBytes);
		return packet;
	}

	private MsgHeader809Gov deserializeHeader(byte[] headerBytes) {
		MsgHeader809Gov header = new MsgHeader809Gov();
		header.setMsgLength(ByteArrayUtil.getUnsignedInt(headerBytes, 0));
		header.setMsgNo(ByteArrayUtil.getUnsignedInt(headerBytes, 4));
		header.setMsgId(ByteArrayUtil.getUnsignedShort(headerBytes, 8));
		header.setGnssPlatformId(GnssPlatformIdGov809.build(ByteArrayUtil.getUnsignedInt(headerBytes, 10)));
		header.setVersion(ByteArrayUtil.copyOfRange(headerBytes, 14, 17));
		header.setEncrypt((ByteArrayUtil.getByte(headerBytes, 17) & 0b1) == 1);
		header.setEncryptKey(ByteArrayUtil.getUnsignedInt(headerBytes, 18));
		return header;
	}

	private byte[] serializeHeader(MsgHeader809Gov header) {
		ByteBuffer buffer = ByteBuffer.allocate(22);
		buffer.putInt((int) header.getMsgLength());
		buffer.putInt((int) header.getMsgNo());
		buffer.putShort((short) header.getMsgId());
		buffer.putInt((int) header.getGnssPlatformId().getId());
		buffer.put(header.getVersion());
		buffer.put((byte) (header.isEncrypt() ? 1 : 0));
		buffer.putInt((int) header.getEncryptKey());
		return buffer.array();
	}

	/**
	 * 转义处理
	 * 
	 * @param frame
	 * @return
	 */
	private byte[] escape(byte[] frame) {
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
	private byte[] unescape(byte[] frame) {
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

	private byte[] encrypt(long encryptKey, byte[] original) {
		return encrypt(m1, ia1, ic1, encryptKey, original);
	}

	private byte[] encrypt(long m1, long ia1, long ic1, long encryptKey, byte[] original) {
		int idx = 0;
		if (encryptKey == 0) {
			encryptKey = 1;
		}
		if (m1 == 0) {
			m1 = 1;
		}
		while (idx < original.length) {
			encryptKey = ia1 * (encryptKey % m1) + ic1;
			original[idx] ^= (encryptKey >> 20) & 0xff;
		}
		return original;
	}

	private int crcCcitt(byte[] bytes) {
		return crcCcitt(2, bytes);
	}

	private int crcCcitt(int flag, byte[] bytes) {
		int crc = 0x00; // initial value
		int polynomial = 0x1021;
		switch (flag) {
		case 1:
			crc = 0x00;
			break;
		case 2:
			crc = 0xFFFF;
			break;
		case 3:
			crc = 0x1D0F;
			break;
		}
		for (int index = 0; index < bytes.length; index++) {
			byte b = bytes[index];
			for (int i = 0; i < 8; i++) {
				boolean bit = ((b >> (7 - i) & 1) == 1);
				boolean c15 = ((crc >> 15 & 1) == 1);
				crc <<= 1;
				if (c15 ^ bit)
					crc ^= polynomial;
			}
		}
		crc &= 0xffff;
		return crc;
	}

}
