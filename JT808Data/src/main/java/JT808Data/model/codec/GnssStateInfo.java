package JT808Data.model.codec;
/**
 * 位置状态标志位
 * 
 * @author 
 *
 */
public class GnssStateInfo {

	private int accOn;
	private int locating;
	private int southLatitude;
	private int westLongitude;
	private int operationStopped;
	private int positionEncrypted;
	private int loadType;
	private int vehicleFuelBreak;
	private int vehicleCircuitBreak;
	private int vehicleDoorLocked;
	private int door1Opened;
	private int door2Opened;
	private int door3Opened;
	private int door4Opened;
	private int door5Opened;
	private int useGPS;
	private int useBD;
	private int useGLONASS;
	private int useGalileo;

	public GnssStateInfo(int bits) {
		accOn = bits & 0x01;
		locating = bits >> 1 & 0x01;
		southLatitude = bits >> 2 & 0x01;
		westLongitude = bits >> 3 & 0x01;
		operationStopped = bits >> 4 & 0x01;
		positionEncrypted = bits >> 5 & 0x01;
		loadType = bits >> 8 & 0x03;
		vehicleFuelBreak = bits >> 10 & 0x01;
		vehicleCircuitBreak = bits >> 11 & 0x01;
		vehicleDoorLocked = bits >> 12 & 0x01;
		door1Opened = bits >> 13 & 0x01;
		door2Opened = bits >> 14 & 0x01;
		door3Opened = bits >> 15 & 0x01;
		door4Opened = bits >> 16 & 0x01;
		door5Opened = bits >> 17 & 0x01;
		useGPS = bits >> 18 & 0x01;
		useBD = bits >> 19 & 0x01;
		useGLONASS = bits >> 20 & 0x01;
		useGalileo = bits >> 21 & 0x01;
	}

	public boolean isAccOn() {
		return accOn == 1;
	}

	public boolean isLocating() {
		return locating == 1;
	}

	public boolean isSouthLatitude() {
		return southLatitude == 1;
	}

	public boolean isNorthLatitude() {
		return southLatitude == 0;
	}

	public boolean isWestLongitude() {
		return westLongitude == 1;
	}

	public boolean isEastLongitude() {
		return westLongitude == 0;
	}

	public boolean isOperationStopped() {
		return operationStopped == 0;
	}

	public boolean isPositionEncrypted() {
		return positionEncrypted == 1;
	}

	public int getLoadType() {
		return loadType;
	}

	public boolean isVehicleFuelBreak() {
		return vehicleFuelBreak == 1;
	}

	public boolean isVehicleCircuitBreak() {
		return vehicleCircuitBreak == 1;
	}

	public boolean isVehicleDoorLocked() {
		return vehicleDoorLocked == 1;
	}

	public boolean isDoor1Opened() {
		return door1Opened == 1;
	}

	public boolean isDoor2Opened() {
		return door2Opened == 1;
	}

	public boolean isDoor3Opened() {
		return door3Opened == 1;
	}

	public boolean isDoor4Opened() {
		return door4Opened == 1;
	}

	public boolean isDoor5Opened() {
		return door5Opened == 1;
	}

	public boolean isUseGPS() {
		return useGPS == 1;
	}

	public boolean isUseBD() {
		return useBD == 1;
	}

	public boolean isUseGLONASS() {
		return useGLONASS == 1;
	}

	public boolean isUseGalileo() {
		return useGalileo == 1;
	}

	@Override
	public String toString() {
		return "GnssStateInfo [accOn=" + accOn + ", locating=" + locating + ", southLatitude=" + southLatitude
				+ ", westLongitude=" + westLongitude + ", operationStopped=" + operationStopped + ", positionEncrypted="
				+ positionEncrypted + ", loadType=" + loadType + ", vehicleFuelBreak=" + vehicleFuelBreak
				+ ", vehicleCircuitBreak=" + vehicleCircuitBreak + ", vehicleDoorLocked=" + vehicleDoorLocked
				+ ", door1Opened=" + door1Opened + ", door2Opened=" + door2Opened + ", door3Opened=" + door3Opened
				+ ", door4Opened=" + door4Opened + ", door5Opened=" + door5Opened + ", useGPS=" + useGPS + ", useBD="
				+ useBD + ", useGLONASS=" + useGLONASS + ", useGalileo=" + useGalileo + "]";
	}
	

}
