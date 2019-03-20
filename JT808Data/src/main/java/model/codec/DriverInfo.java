package model.codec;

import java.util.Date;

import Utils.BcdCodeUtil;
import Utils.ByteArrayUtil;

/**
 * º› ª‘±–≈œ¢
 * @author Administrator
 *
 */
public class DriverInfo {

	private String driverName;
	private String qualificationCode;
	private String orgName;
	private Date expiryDate;

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public static DriverInfo restore(byte[] bytes) {
		DriverInfo driverInfo = new DriverInfo();
		int dl = ByteArrayUtil.getUnsignedByte(bytes, 0);
		driverInfo.setDriverName(ByteArrayUtil.toString(ByteArrayUtil.copyOfRange(bytes, 1, 1 + dl)));
		driverInfo.setQualificationCode(ByteArrayUtil.toString(ByteArrayUtil.copyOfRange(bytes, 1 + dl, 21 + dl)));
		int ol = ByteArrayUtil.getUnsignedByte(bytes, 21 + dl);
		driverInfo.setOrgName(ByteArrayUtil.toString(ByteArrayUtil.copyOfRange(bytes, 22 + dl, 22 + dl + ol)));
		driverInfo.setExpiryDate(BcdCodeUtil.bcdToDate(ByteArrayUtil.copyOfRange(bytes, 22 + dl + ol, bytes.length)));
		return driverInfo;
	}

	@Override
	public String toString() {
		return "DriverInfo [driverName=" + driverName + ", qualificationCode=" + qualificationCode + ", orgName="
				+ orgName + ", expiryDate=" + expiryDate + "]";
	}
	
}
