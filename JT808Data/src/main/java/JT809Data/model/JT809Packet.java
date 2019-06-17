package JT809Data.model;

public class JT809Packet {

	private MsgHeader header;
	private byte[] msgBodyBytes;

	public JT809Packet(MsgHeader header, byte[] msgBodyBytes) {
		this.header = header;
		this.msgBodyBytes = msgBodyBytes;
	}

	public int getMsgId() {
		return header.getMsgId();
	}

	public GnssPlatformId getGnssPlatformId() {
		return header.getGnssPlatformId();
	}

	public MsgHeader getHeader() {
		return header;
	}

	public byte[] getMsgBody() {
		return msgBodyBytes;
	}

}
