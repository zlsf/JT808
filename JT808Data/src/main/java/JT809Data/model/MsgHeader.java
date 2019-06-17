package JT809Data.model;

public class MsgHeader {
	private long msgLength;
	private long msgNo;
	private int msgId;
	private GnssPlatformId gnssPlatformId;
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

	public GnssPlatformId getGnssPlatformId() {
		return gnssPlatformId;
	}

	public void setGnssPlatformId(GnssPlatformId gnssPlatformId) {
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
