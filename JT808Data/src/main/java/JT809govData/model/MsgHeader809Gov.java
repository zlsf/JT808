package JT809govData.model;

public class MsgHeader809Gov {
	private long msgLength;
	private long msgNo;
	private int msgId;
	private GnssPlatformIdGov809 gnssPlatformId;
	private byte[] versionFlag;
	private boolean encrypt;
	private long encryptKey;

	public long getMsgLength() {
		return msgLength;
	}

	public void setMsgLength(long msgLength) {
		this.msgLength = msgLength;
	}

	public long getMsgNo() {
		return msgNo;
	}

	public void setMsgNo(long msgNo) {
		this.msgNo = msgNo;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public GnssPlatformIdGov809 getGnssPlatformId() {
		return gnssPlatformId;
	}

	public void setGnssPlatformId(GnssPlatformIdGov809 gnssPlatformId) {
		this.gnssPlatformId = gnssPlatformId;
	}

	public byte[] getVersionFlag() {
		return versionFlag;
	}

	public void setVersionFlag(byte[] versionFlag) {
		this.versionFlag = versionFlag;
	}

	public boolean isEncrypt() {
		return encrypt;
	}

	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}

	public long getEncryptKey() {
		return encryptKey;
	}

	public void setEncryptKey(long encryptKey) {
		this.encryptKey = encryptKey;
	}

}
