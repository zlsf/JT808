package JT809Data.netty.msghandler.base;


import JT809Data.model.GnssPlatformId;

public class DownBaseMsgData {

	private int msgId;
	private GnssPlatformId gnssPlatformId;
	private String vehicleNo;
	private int vehicleColor;
	private int dataType;
	private byte[] dataBytes;

	public DownBaseMsgData(int msgId, GnssPlatformId gnssPlatformId, String vehicleNo, int vehicleColor, int dataType, byte[] data) {
		this.msgId = msgId;
		this.gnssPlatformId = gnssPlatformId;
		this.vehicleNo = vehicleNo;
		this.vehicleColor = vehicleColor;
		this.dataType = dataType;
		this.dataBytes = data;
	}

	public int getMsgId() {
		return msgId;
	}

	public GnssPlatformId getGnssPlatformId() {
		return gnssPlatformId;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public int getVehicleColor() {
		return vehicleColor;
	}

	public int getDataType() {
		return dataType;
	}

	public byte[] getDataBytes() {
		return dataBytes;
	}
}
