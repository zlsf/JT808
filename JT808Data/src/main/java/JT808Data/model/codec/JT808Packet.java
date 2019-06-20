
package JT808Data.model.codec;

import java.nio.ByteBuffer;

import Utils.ByteArrayUtil;

/**
 * 808的包协议
 * @author Administrator
 *
 */
public class JT808Packet {

	private Header header;
	private byte[] msgBodyBytes;
	private int checksum;

	public JT808Packet() {
	}

	public JT808Packet(int msgId, TerminalId terminalId) {
		header = new Header(msgId, terminalId);
	}

	public MessageId getMessageId() {
		return new MessageId(header.getMsgId(), header.getTerminalId());
	}

	public int getMsgId() {
		return header.getMsgId();
	}

	public int getMsgNo() {
		return header.getMsgNo();
	}

	public void setMsgNo(int msgNo) {
		header.setMsgNo(msgNo);
	}

	public TerminalId getTerminalId() {
		return header.getTerminalId();
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public boolean isSubpacket() {
		return header.isSubpacket();
	}

	public int getSubpacketCount() {
		return header.getSubpacketCount();
	}

	public int getSubpacketNo() {
		return header.getSubpacketNo();
	}

	public byte[] getMsgBodyBytes() {
		return msgBodyBytes;
	}

	void setMsgBodyBytes(byte[] msgBodyBytes) {
		this.msgBodyBytes = msgBodyBytes;
	}

	public int getChecksum() {
		return checksum;
	}

	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}

	public byte[] toByteArray() {
		byte[] unescapedBytes = ByteArrayUtil.concact(header.toByteArray(), msgBodyBytes,
				new byte[] { (byte) checksum });
		checksum = JT808PacketCodec.calculateChecksum(unescapedBytes, 0, unescapedBytes.length - 1);
		unescapedBytes[unescapedBytes.length - 1] = (byte) checksum;
		return JT808PacketCodec.escape(unescapedBytes);
	}

	public static class Header {

		private int msgId;
		private int msgBodyLength;
		private int encryptionType;
		private boolean subpacket;
		private int reservedBit;
		private TerminalId terminalId;
		private int msgNo;

		private int subpacketCount = 0;
		private int subpacketNo = 1; // 从1开始 1-index

		Header() {
		}

		Header(int msgId, TerminalId terminalId) {
			this.msgId = msgId;
			this.terminalId = terminalId;
		}

		public int getMsgId() {
			return msgId;
		}

		public void setMsgId(int msgId) {
			this.msgId = msgId;
		}

		public void setMsgBodyPropsField(int msgBodyPropsField) {
			this.msgBodyLength = msgBodyPropsField & 0b1111111111;
			this.encryptionType = (msgBodyPropsField >>> 10) & 0b111;
			this.subpacket = ((msgBodyPropsField >>> 13) & 0b1) == 1;
			this.reservedBit = (msgBodyPropsField >>> 14) & 0b11;
		}

		public int getMsgBodyLength() {
			return msgBodyLength;
		}

		public void setMsgBodyLength(int msgBodyLength) {
			this.msgBodyLength = msgBodyLength;
		}

		public int getEncryptionType() {
			return encryptionType;
		}

		public void setEncryptionType(int encryptionType) {
			this.encryptionType = encryptionType;
		}

		public boolean isSubpacket() {
			return subpacket;
		}

		public void setSubpacket(boolean subpacket) {
			this.subpacket = subpacket;
		}

		public int getReservedBit() {
			return reservedBit;
		}

		public void setReservedBit(int reservedBit) {
			this.reservedBit = reservedBit;
		}

		public TerminalId getTerminalId() {
			return terminalId;
		}

		public void setTerminalId(TerminalId terminalId) {
			this.terminalId = terminalId;
		}

		public int getMsgNo() {
			return msgNo;
		}

		public void setMsgNo(int msgNo) {
			this.msgNo = msgNo;
		}

		public int getSubpacketCount() {
			return subpacketCount;
		}

		public void setSubpacketCount(int subpacketCount) {
			this.subpacketCount = subpacketCount;
		}

		public int getSubpacketNo() {
			return subpacketNo;
		}

		public void setSubpacketNo(int subpacketNo) {
			this.subpacketNo = subpacketNo;
		}

		public byte[] toByteArray() {
			int headerLength = 12;
			if (subpacket) {
				headerLength = 16;
			}
			ByteBuffer buffer = ByteBuffer.allocate(headerLength);
			buffer.putShort((short) msgId);
			int msgBodyPropsField = (msgBodyLength & 0b1111111111) | (encryptionType & 0b111) << 10
					| (subpacket ? 1 << 13 : 0) | (reservedBit & 0b11) << 14;
			buffer.putShort((short) msgBodyPropsField);
			buffer.put(terminalId.toByteArray());
			buffer.putShort((short) msgNo);
			if (subpacket) {
				buffer.putShort((short) subpacketCount);
				buffer.putShort((short) subpacketNo);
			}
			return buffer.array();
		}
	}
}
