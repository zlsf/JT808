package customer.server.model;

import java.util.Arrays;

/**
 * 设备标识
 * 
 * @author cuillgln
 * 
 */
public class DeviceId {

	private byte[] id = new byte[6];

	public DeviceId(byte[] bcdBytes) {

		this.id = Arrays.copyOf(bcdBytes, 6);
	}

	public static DeviceId createStopId(String idString) {
		char[] idca = idString.toCharArray();
		byte[] idba = new byte[idca.length];
		for (int i = 0; i < idba.length; i++) {
			byte b = Byte.parseByte(idca[i] + "");
			idba[i] = b;
		}
		return new DeviceId(idba);
	}

	public byte[] toByteArray() {
		return id;
	}

	public String getId() {
		return bcdToString(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeviceId other = (DeviceId) obj;
		if (!Arrays.equals(id, other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return bcdToString(id);
	}

	public static String bcdToString(byte[] id) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < id.length; i++) {
			sb.append(bcdToInteger(id[i]));
		}
		return sb.toString();
	}

	public static int bcdToInteger(byte b) {
		int low = b & 0x0F;
		int high = (b >>> 4) & 0x0F;
		return high * 10 + low;
	}
}
