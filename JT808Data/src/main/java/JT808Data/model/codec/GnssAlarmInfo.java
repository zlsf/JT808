
package JT808Data.model.codec;

/**
 * 位置报警标志位
 * 
 * @author cuillgln
 *
 */
public class GnssAlarmInfo {

	private int emegency;
	private int overspeed;
	private int fatigueDriving;
	private int dangerEarlyWarning;
	private int gnssModuleFault;
	private int gnssAntennaBreak;
	private int gnssAntennaShorted;
	private int terminalUndervoltage;
	private int terminalPowerdown;
	private int terminalDisplayFault;
	private int ttsModuleFault;
	private int cameraModuleFault;
	private int icModuleFault;
	private int overspeedEarlyWarning;
	private int fatigueDrivingEarlyWarning;

	private int accumDriveTimeout;
	private int parkingTimeout;
	private int areaInOut;
	private int lineInOut;
	private int segmentDriveDuration;
	private int lineDiverge;
	private int vehicleVSSFault;
	private int vehicleFuelQuantityAbnormal;
	private int vehicleStolen;
	private int illegalPoweron;
	private int illegalDisplacement;
	private int crashEarlyWarning;
	private int rolloverEarlyWarning;
	private int illegalDoorOpen;

	public GnssAlarmInfo(int bits) {
		emegency = bits & 0x01;
		overspeed = bits >> 1 & 0x01;
		fatigueDriving = bits >> 2 & 0x01;
		dangerEarlyWarning = bits >> 3 & 0x01;
		gnssModuleFault = bits >> 4 & 0x01;
		gnssAntennaBreak = bits >> 5 & 0x01;
		gnssAntennaShorted = bits >> 6 & 0x01;
		terminalUndervoltage = bits >> 7 & 0x01;
		terminalPowerdown = bits >> 8 & 0x01;
		terminalDisplayFault = bits >> 9 & 0x01;
		ttsModuleFault = bits >> 10 & 0x01;
		cameraModuleFault = bits >> 11 & 0x01;
		icModuleFault = bits >> 12 & 0x01;
		overspeedEarlyWarning = bits >> 13 & 0x01;
		fatigueDrivingEarlyWarning = bits >> 14 & 0x01;

		accumDriveTimeout = bits >> 18 & 0x01;
		parkingTimeout = bits >> 18 & 0x01;
		areaInOut = bits >> 20 & 0x01;
		lineInOut = bits >> 21 & 0x01;
		segmentDriveDuration = bits >> 22 & 0x01;
		lineDiverge = bits >> 23 & 0x01;
		vehicleVSSFault = bits >> 24 & 0x01;
		vehicleFuelQuantityAbnormal = bits >> 25 & 0x01;
		vehicleStolen = bits >> 26 & 0x01;
		illegalPoweron = bits >> 27 & 0x01;
		illegalDisplacement = bits >> 28 & 0x01;
		crashEarlyWarning = bits >> 29 & 0x01;
		rolloverEarlyWarning = bits >> 30 & 0x01;
		illegalDoorOpen = bits >> 31 & 0x01;
	}

	public boolean isEmegency() {
		return emegency == 1;
	}

	public boolean isOverspeed() {
		return overspeed == 1;
	}

	public boolean isFatigueDriving() {
		return fatigueDriving == 1;
	}

	public boolean isDangerEarlyWarning() {
		return dangerEarlyWarning == 1;
	}

	public boolean isGnssModuleFault() {
		return gnssModuleFault == 1;
	}

	public boolean isGnssAntennaBreak() {
		return gnssAntennaBreak == 1;
	}

	public boolean isGnssAntennaShorted() {
		return gnssAntennaShorted == 1;
	}

	public boolean isTerminalUndervoltage() {
		return terminalUndervoltage == 1;
	}

	public boolean isTerminalPowerdown() {
		return terminalPowerdown == 1;
	}

	public boolean isTerminalDisplayFault() {
		return terminalDisplayFault == 1;
	}

	public boolean isTtsModuleFault() {
		return ttsModuleFault == 1;
	}

	public boolean isCameraModuleFault() {
		return cameraModuleFault == 1;
	}

	public boolean isIcModuleFault() {
		return icModuleFault == 1;
	}

	public boolean isOverspeedEarlyWarning() {
		return overspeedEarlyWarning == 1;
	}

	public boolean isFatigueDrivingEarlyWarning() {
		return fatigueDrivingEarlyWarning == 1;
	}

	public boolean isAccumDriveTimeout() {
		return accumDriveTimeout == 1;
	}

	public boolean isParkingTimeout() {
		return parkingTimeout == 1;
	}

	public boolean isAreaInOut() {
		return areaInOut == 1;
	}

	public boolean isLineInOut() {
		return lineInOut == 1;
	}

	public boolean isSegmentDriveDuration() {
		return segmentDriveDuration == 1;
	}

	public boolean isLineDiverge() {
		return lineDiverge == 1;
	}

	public boolean isVehicleVSSFault() {
		return vehicleVSSFault == 1;
	}

	public boolean isVehicleFuelQuantityAbnormal() {
		return vehicleFuelQuantityAbnormal == 1;
	}

	public boolean isVehicleStolen() {
		return vehicleStolen == 1;
	}

	public boolean isIllegalPoweron() {
		return illegalPoweron == 1;
	}

	public boolean isIllegalDisplacement() {
		return illegalDisplacement == 1;
	}

	public boolean isCrashEarlyWarning() {
		return crashEarlyWarning == 1;
	}

	public boolean isRolloverEarlyWarning() {
		return rolloverEarlyWarning == 1;
	}

	public boolean isIllegalDoorOpen() {
		return illegalDoorOpen == 1;
	}

	@Override
	public String toString() {
		return "GnssAlarmInfo [emegency=" + emegency + ", overspeed=" + overspeed + ", fatigueDriving=" + fatigueDriving
				+ ", dangerEarlyWarning=" + dangerEarlyWarning + ", gnssModuleFault=" + gnssModuleFault
				+ ", gnssAntennaBreak=" + gnssAntennaBreak + ", gnssAntennaShorted=" + gnssAntennaShorted
				+ ", terminalUndervoltage=" + terminalUndervoltage + ", terminalPowerdown=" + terminalPowerdown
				+ ", terminalDisplayFault=" + terminalDisplayFault + ", ttsModuleFault=" + ttsModuleFault
				+ ", cameraModuleFault=" + cameraModuleFault + ", icModuleFault=" + icModuleFault
				+ ", overspeedEarlyWarning=" + overspeedEarlyWarning + ", fatigueDrivingEarlyWarning="
				+ fatigueDrivingEarlyWarning + ", accumDriveTimeout=" + accumDriveTimeout + ", parkingTimeout="
				+ parkingTimeout + ", areaInOut=" + areaInOut + ", lineInOut=" + lineInOut + ", segmentDriveDuration="
				+ segmentDriveDuration + ", lineDiverge=" + lineDiverge + ", vehicleVSSFault=" + vehicleVSSFault
				+ ", vehicleFuelQuantityAbnormal=" + vehicleFuelQuantityAbnormal + ", vehicleStolen=" + vehicleStolen
				+ ", illegalPoweron=" + illegalPoweron + ", illegalDisplacement=" + illegalDisplacement
				+ ", crashEarlyWarning=" + crashEarlyWarning + ", rolloverEarlyWarning=" + rolloverEarlyWarning
				+ ", illegalDoorOpen=" + illegalDoorOpen + "]";
	}

}
