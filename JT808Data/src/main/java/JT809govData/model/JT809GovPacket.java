package JT809govData.model;

public class JT809GovPacket {

	private MsgHeader809Gov header;
	private byte[] msgBodyBytes;

	public JT809GovPacket(MsgHeader809Gov header, byte[] msgBodyBytes) {
		this.header = header;
		this.msgBodyBytes = msgBodyBytes;
	}

	public int getMsgId() {
		return header.getMsgId();
	}

	public GnssPlatformIdGov809 getGnssPlatformId() {
		return header.getGnssPlatformId();
	}

	public MsgHeader809Gov getHeader() {
		return header;
	}

	public byte[] getMsgBody() {
		return msgBodyBytes;
	}

}
