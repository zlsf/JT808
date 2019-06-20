
package Utils;

import java.util.Calendar;
import java.util.Date;

/**
 * BCD工具类
 */
public final class BcdCodeUtil {

	/**
	 * Bcd to string.
	 *
	 * @param bcdBytes the bcd bytes
	 * @return the string
	 */
	public static String bcdToString(byte[] bcdBytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bcdBytes.length; i++) {
			String str = String.format("%02d", bcdToInteger(bcdBytes[i]));
			sb.append(str);
		}
		return sb.toString();
	}

	/**
	 * Bcd to integer.
	 *
	 * @param oneByte the one byte
	 * @return the int
	 */
	public static int bcdToInteger(byte oneByte) {
		int low = oneByte & 0x0F;
		int high = (oneByte >>> 4) & 0x0F;
		return high * 10 + low;
	}

	/**
	 * String to bcd.
	 *
	 * @param str the str
	 * @return the byte[]
	 */
	public static byte[] stringToBcd(String str) {
		char[] idca = str.toCharArray();
		byte[] idba = new byte[idca.length / 2];
		for (int i = 0; i < idba.length; i++) {
			int b = idca[2 * i] - '0';
			b <<= 4;
			b += (idca[2 * i + 1] - '0');
			idba[i] = (byte) b;
		}
		return idba;
	}

	/**
	 * Int to bcd.
	 *
	 * @param digit the digit
	 * @return the byte
	 */
	public static byte intToBcd(int digit) {
		int high = digit / 10;
		int low = digit % 10;
		return (byte) (high << 4 | low);
	}

	/**
	 * BCD[6] 转时间.
	 *
	 * @param bcdBytes the bcd bytes
	 * @return the date
	 */
	public static Date bcdToDateTime(byte[] bcdBytes) {
		String bcd = BcdCodeUtil.bcdToString(bcdBytes);
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int yearPrefix = year / 100 * 100;
		c.clear();
		c.set(Calendar.YEAR, yearPrefix + Integer.parseInt(bcd.substring(0, 2)));
		c.set(Calendar.MONTH, Integer.parseInt(bcd.substring(2, 4)) - 1);
		c.set(Calendar.DATE, Integer.parseInt(bcd.substring(4, 6)));
		c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(bcd.substring(6, 8)));
		c.set(Calendar.MINUTE, Integer.parseInt(bcd.substring(8, 10)));
		c.set(Calendar.SECOND, Integer.parseInt(bcd.substring(10, 12)));
		return c.getTime();
	}

	/**
	 * Date time to bcd.
	 *
	 * @param time the time
	 * @return the byte[]
	 */
	public static byte[] dateTimeToBcd(Date time) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(time);
		byte year = intToBcd(c.get(Calendar.YEAR) % 100);
		byte month = intToBcd(c.get(Calendar.MONTH) + 1);
		byte date = intToBcd(c.get(Calendar.DATE));
		byte hour = intToBcd(c.get(Calendar.HOUR_OF_DAY));
		byte min = intToBcd(c.get(Calendar.MINUTE));
		byte second = intToBcd(c.get(Calendar.SECOND));
		return new byte[] { year, month, date, hour, min, second };
	}

	/**
	 * Bcd to date.
	 *
	 * @param bcdBytes the bcd bytes
	 * @return the date
	 */
	public static Date bcdToDate(byte[] bcdBytes) {
		String bcd = BcdCodeUtil.bcdToString(bcdBytes);
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(Calendar.YEAR, Integer.parseInt(bcd.substring(0, 4)));
		c.set(Calendar.MONTH, Integer.parseInt(bcd.substring(2, 4)) - 1);
		c.set(Calendar.DATE, Integer.parseInt(bcd.substring(4, 6)));
		return c.getTime();
	}

	/**
	 * Date to bcd.
	 *
	 * @param time the time
	 * @return the byte[]
	 */
	public static byte[] dateToBcd(Date time) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(time);
		byte yearHigh = intToBcd(c.get(Calendar.YEAR) / 100);
		byte year = intToBcd(c.get(Calendar.YEAR) % 100);
		byte month = intToBcd(c.get(Calendar.MONTH) + 1);
		byte date = intToBcd(c.get(Calendar.DATE));
		return new byte[] { yearHigh, year, month, date };
	}
}
