
package JT809govData.netty.ext;

import JT809govData.model.GnssPlatformIdGov809;

public class UpExgMsgData809Gov {

	private int msgId;
	private GnssPlatformIdGov809 gnssPlatformId;
	private String vehicleNo;
	private int vehicleColor;
	private int dataType;
	private byte[] dataBytes;

	public UpExgMsgData809Gov(int msgId, GnssPlatformIdGov809 gnssPlatformId, String vehicleNo, int vehicleColor,
			int dataType, byte[] data) {
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

	public GnssPlatformIdGov809 getGnssPlatformId() {
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
