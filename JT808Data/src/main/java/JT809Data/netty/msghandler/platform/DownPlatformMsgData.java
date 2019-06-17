package JT809Data.netty.msghandler.platform;

import JT809Data.model.GnssPlatformId;

public class DownPlatformMsgData {

	private int msgId;
	private GnssPlatformId gnssPlatformId;
	private int dataType;
	private byte[] dataBytes;

	public DownPlatformMsgData(int msgId, GnssPlatformId gnssPlatformId, int dataType, byte[] data) {
		this.msgId = msgId;
		this.gnssPlatformId = gnssPlatformId;
		this.dataType = dataType;
		this.dataBytes = data;
	}

	public int getMsgId() {
		return msgId;
	}

	public GnssPlatformId getGnssPlatformId() {
		return gnssPlatformId;
	}

	public int getDataType() {
		return dataType;
	}

	public byte[] getDataBytes() {
		return dataBytes;
	}
}
