package Utils;


/**
 * 16进制转换工具类
 * @author    ligenglin1
 * 
 * <p>Modification History:</p>
 * <p>Date              Author      Description</p>
 * <p>------------------------------------------------------------------</p>
 * <p>2016-10-19       ligenglin1        new    </p>
 * <p>  </p>
 */
public final class HexUtil {

	private final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	/**
	 * 4字节无符号整数转16进制, 位数不够前面补0
	 * @param data 无符号整数
	 * @return
	 */
	public static String unsignedIntegerToHexString(long data) {
		char[] buf = new char[8];
		int charPos = buf.length;
		do {
			buf[--charPos] = digits[(int) (data & 0x0f)];
			data >>>= 4;
		} while (charPos > 0);

		return new String(buf);
	}

	/**
	 * 2字节无符号整数转16进制, 位数不够前面补0
	 * @param data 无符号整数
	 * @return
	 */
	public static String unsignedShortToHexString(int data) {
		char[] buf = new char[4];
		int charPos = buf.length;
		do {
			buf[--charPos] = digits[data & 0x0f];
			data >>>= 4;
		} while (charPos > 0);

		return new String(buf);
	}

	/**
	 * 单字节转16进制表示
	 * @param data
	 * @return
	 */
	public static String byteToHexString(byte data) {
		char[] buf = new char[2];
		buf[0] = digits[(0xf0 & data) >>> 4]; // 高位
		buf[1] = digits[0x0f & data];
		return new String(buf);
	}

	/**
	 * 字节数组转16进制表示
	 * @param data
	 * @return
	 */
	public static String byteArrayToHexString(byte[] data) {
		char[] buf = new char[data.length << 1];
		for (int i = 0, j = 0; i < data.length; i++) {
			buf[j++] = digits[(0xf0 & data[i]) >>> 4]; // 高位
			buf[j++] = digits[0x0f & data[i]];
		}
		return new String(buf);
	}

	public static long decodeHex4Long(String hexString) {
		return Long.parseLong(hexString, 16);
	}

	public static int decodeHex4Int(String hexString) {
		return Integer.parseInt(hexString, 16);
	}

	public static byte decodeHex4Byte(String hexString) {
		return Integer.valueOf(hexString, 16).byteValue();
	}

	/**
	 * @param hexString
	 * @return
	 */
	public static byte[] decodeHex(String hexString) {
		char[] data = hexString.toCharArray();
		int len = data.length;
		final byte[] out = new byte[len >> 1];

		// two characters form the hex value.
		for (int i = 0, j = 0; j < len; i++) {
			int f = toDigit(data[j]) << 4;
			j++;
			f = f | toDigit(data[j]);
			j++;
			out[i] = (byte) (f & 0xFF);
		}

		return out;
	}

	private static int toDigit(final char ch) {
		if (ch >= 'a') {
			return ch - 'a' + 10 & 0xF;
		}
		if (ch >= 'A') {
			return ch - 'A' + 10 & 0xF;
		}
		return ch - '0' & 0xF;
	}
	
	public static void main(String[] args) {
		String hexString = "5B";
		byte[] ba = decodeHex(hexString);
		
		System.out.println(Integer.toBinaryString(ba[0]));
		System.out.println(Integer.toHexString(ba[0]));
	}
}
