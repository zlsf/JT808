
package model.codec;

import java.math.BigDecimal;
import java.util.Date;

import Utils.BcdCodeUtil;
import Utils.ByteArrayUtil;

/**
 * 定位信息
 * 
 * @author cuillgln
 *
 */
public class GnssPosition {

	private String terminalPhone;
	private GnssAlarmInfo alarm;
	private GnssStateInfo state;
	private long latitude;
	private long longitude;
	private int altitude;
	private int speed;
	private int direction;
	private Date positionTime;

	public String getTerminalPhone() {
		return terminalPhone;
	}

	public void setTerminalPhone(String terminalPhone) {
		this.terminalPhone = terminalPhone;
	}

	public GnssAlarmInfo getAlarm() {
		return alarm;
	}

	public void setAlarm(GnssAlarmInfo alarm) {
		this.alarm = alarm;
	}

	public GnssStateInfo getState() {
		return state;
	}

	public void setState(GnssStateInfo state) {
		this.state = state;
	}

	public long getLatitudeLong() {
		return latitude;
	}

	public BigDecimal getLatitude() {
		return BigDecimal.valueOf(latitude).multiply(new BigDecimal("0.000001"));
	}

	public void setLatitudeLong(long latitude) {
		this.latitude = latitude;
	}

	public long getLongitudeLong() {
		return longitude;
	}

	public BigDecimal getLongitude() {
		return BigDecimal.valueOf(longitude).multiply(new BigDecimal("0.000001"));
	}

	public void setLongitudeLong(long longitude) {
		this.longitude = longitude;
	}

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Date getPositionTime() {
		return positionTime;
	}

	public void setPositionTime(Date positionTime) {
		this.positionTime = positionTime;
	}

	/**
	 * 生成位置
	 * 
	 * @param terminalPhone
	 * @param bytes
	 * @return
	 */
	public static GnssPosition restore(String terminalPhone, byte[] bytes) {
		GnssPosition position = new GnssPosition();
		position.setTerminalPhone(terminalPhone);
		position.setAlarm(new GnssAlarmInfo(ByteArrayUtil.getInt(bytes, 0)));
		position.setState(new GnssStateInfo(ByteArrayUtil.getInt(bytes, 4)));
		position.setLatitudeLong(ByteArrayUtil.getUnsignedInt(bytes, 8));
		position.setLongitudeLong(ByteArrayUtil.getUnsignedInt(bytes, 12));
		position.setAltitude(ByteArrayUtil.getUnsignedShort(bytes, 16));
		position.setSpeed(ByteArrayUtil.getUnsignedShort(bytes, 18));
		position.setDirection(ByteArrayUtil.getUnsignedShort(bytes, 20));
		position.setPositionTime(BcdCodeUtil.bcdToDateTime(ByteArrayUtil.copyOfRange(bytes, 22, 28)));
		return position;
	}

	@Override
	public String toString() {
		return "GnssPosition [terminalPhone=" + terminalPhone + ", alarm=" + alarm + ", state=" + state + ", latitude="
				+ latitude + ", longitude=" + longitude + ", altitude=" + altitude + ", speed=" + speed + ", direction="
				+ direction + ", positionTime=" + positionTime + "]";
	}

}
