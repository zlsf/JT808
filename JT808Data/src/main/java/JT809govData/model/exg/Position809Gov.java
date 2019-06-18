package JT809govData.model.exg;

import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.util.Calendar;

import Utils.ByteArrayUtil;


public class Position809Gov {

	private long gnssPlatformId;
	private String vehicleNo;
	private int vehicleColor;
	private boolean encrypt;
	private Timestamp positionTime;
	private long longitude;
	private long latitude;
	private int speed;
	private int runningSpeed;
	private long totalDistance;
	private int direction;
	private int altitude;
	private long state;
	private long alarm;

	public Position809Gov() {

	}

	public Position809Gov(long gnssPlatformId, String vehicleNo, int vehicleColor, boolean encrypt, Timestamp positionTime, long longitude, long latitude, int speed, int runningSpeed, long totalDistance, int direction, int altitude, long state, long alarm) {
		super();
		this.gnssPlatformId = gnssPlatformId;
		this.vehicleNo = vehicleNo;
		this.vehicleColor = vehicleColor;
		this.encrypt = encrypt;
		this.positionTime = positionTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.speed = speed;
		this.runningSpeed = runningSpeed;
		this.totalDistance = totalDistance;
		this.direction = direction;
		this.altitude = altitude;
		this.state = state;
		this.alarm = alarm;
	}

	public long getGnssPlatformId() {
		return gnssPlatformId;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public int getVehicleColor() {
		return vehicleColor;
	}

	public boolean isEncrypt() {
		return encrypt;
	}

	public Timestamp getPositionTime() {
		return positionTime;
	}

	public long getLongitude() {
		return longitude;
	}

	public long getLatitude() {
		// return new
		// BigDecimal("0.000001").multiply(BigDecimal.valueOf(latitude));
		return latitude;
	}

	public int getSpeed() {
		return speed;
	}

	public int getRunningSpeed() {
		return runningSpeed;
	}

	public long getTotalDistance() {
		return totalDistance;
	}

	public int getDirection() {
		return direction;
	}

	public int getAltitude() {
		return altitude;
	}

	public long getState() {
		return state;
	}

	public long getAlarm() {
		return alarm;
	}

	public byte[] toByteArray() {
		ByteBuffer buffer = ByteBuffer.allocate(36);
		buffer.put((byte) (encrypt ? 1 : 0));

		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(positionTime);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH + 1);
		int year = c.get(Calendar.YEAR);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);

		buffer.put((byte) day);
		buffer.put((byte) month);
		buffer.putShort((short) year);
		buffer.put((byte) hour);
		buffer.put((byte) minute);
		buffer.put((byte) second);
		buffer.putInt((int) longitude);
		buffer.putInt((int) latitude);
		buffer.putShort((short) speed);
		buffer.putShort((short) runningSpeed);
		buffer.putInt((int) totalDistance);
		buffer.putShort((short) direction);
		buffer.putShort((short) altitude);
		buffer.putInt((int) state);
		buffer.putInt((int) alarm);
		return buffer.array();
	}

	public static Position809Gov restoreFromByteArray(long gnssPlatformId, String vehicleNo, int vehicleColor, byte[] pdata) {
		return new Position809Gov.Builder()
				.setGnssPlatformId(gnssPlatformId)
				.setVehicleNo(vehicleNo)
				.setVehicleColor(vehicleColor)
				.setEncrypt(ByteArrayUtil.getUnsignedByte(pdata, 0) == 0 ? false : true)
				.setPositionTime(Position809Gov.getDateTimeFromBytes(ByteArrayUtil.copyOfRange(pdata, 1, 8)))
				.setLongitude(ByteArrayUtil.getUnsignedInt(pdata, 8))
				.setLatitude(ByteArrayUtil.getUnsignedInt(pdata, 12))
				.setSpeed(ByteArrayUtil.getUnsignedShort(pdata, 16))
				.setRunningSpeed(ByteArrayUtil.getUnsignedShort(pdata, 18))
				.setTotalDistance(ByteArrayUtil.getUnsignedInt(pdata, 20))
				.setDirection(ByteArrayUtil.getUnsignedShort(pdata, 24))
				.setAltitude(ByteArrayUtil.getUnsignedShort(pdata, 26))
				.setState(ByteArrayUtil.getUnsignedInt(pdata, 28))
				.setAlarm(ByteArrayUtil.getUnsignedInt(pdata, 32))
				.build();
	}

	public static Timestamp getDateTimeFromBytes(byte[] data) {
		int day = ByteArrayUtil.getUnsignedByte(data, 0);
		int month = ByteArrayUtil.getUnsignedByte(data, 1);
		int year = ByteArrayUtil.getUnsignedShort(data, 2);
		int hour = ByteArrayUtil.getUnsignedByte(data, 4);
		int minute = ByteArrayUtil.getUnsignedByte(data, 5);
		int second = ByteArrayUtil.getUnsignedByte(data, 6);
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		return new Timestamp(c.getTimeInMillis());
	}

	public static class Builder {

		private long gnssPlatformId;
		private String vehicleNo;
		private int vehicleColor;
		private boolean encrypt;
		private Timestamp positionTime;
		private long longitude;
		private long latitude;
		private int speed;
		private int runningSpeed;
		private long totalDistance;
		private int direction;
		private int altitude;
		private long state;
		private long alarm;

		public Builder setGnssPlatformId(long gnssPlatformId) {
			this.gnssPlatformId = gnssPlatformId;
			return this;
		}

		public Builder setVehicleNo(String vehicleNo) {
			this.vehicleNo = vehicleNo;
			return this;
		}

		public Builder setVehicleColor(int vehicleColor) {
			this.vehicleColor = vehicleColor;
			return this;
		}

		public Builder setEncrypt(boolean encrypt) {
			this.encrypt = encrypt;
			return this;
		}

		public Builder setPositionTime(Timestamp positionTime) {
			this.positionTime = positionTime;
			return this;
		}

		public Builder setLongitude(long longitude) {
			this.longitude = longitude;
			return this;
		}

		public Builder setLatitude(long latitude) {
			this.latitude = latitude;
			return this;
		}

		public Builder setSpeed(int speed) {
			this.speed = speed;
			return this;
		}

		public Builder setRunningSpeed(int runningSpeed) {
			this.runningSpeed = runningSpeed;
			return this;
		}

		public Builder setTotalDistance(long totalDistance) {
			this.totalDistance = totalDistance;
			return this;
		}

		public Builder setDirection(int direction) {
			this.direction = direction;
			return this;
		}

		public Builder setAltitude(int altitude) {
			this.altitude = altitude;
			return this;
		}

		public Builder setState(long state) {
			this.state = state;
			return this;
		}

		public Builder setAlarm(long alarm) {
			this.alarm = alarm;
			return this;
		}

		public Position809Gov build() {
			return new Position809Gov(gnssPlatformId, vehicleNo, vehicleColor, encrypt, positionTime, longitude, latitude, speed, runningSpeed, totalDistance, direction, altitude, state, alarm);
		}
	}
}
