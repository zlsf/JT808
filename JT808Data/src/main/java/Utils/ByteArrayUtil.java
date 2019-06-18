
package Utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import io.netty.buffer.ByteBufUtil;

// TODO: Auto-generated Javadoc
/**
 * ������.
 *
 * @author Administrator
 */
public final class ByteArrayUtil {

	/**
	 * ��������.
	 *
	 * @param bs the bs
	 * @return the byte[]
	 */
	public static byte[] concact(byte[]... bs) {
		int length = 0;
		for (byte[] bn : bs) {
			length += bn.length;
		}
		byte[] rtn = new byte[length];
		int index = 0;
		for (byte[] bn : bs) {
			System.arraycopy(bn, 0, rtn, index, bn.length);
			index += bn.length;
		}
		return rtn;
	}

	/**
	 *  
	 * ��byteת��Ϊһ������Ϊ8��byte���飬����ÿ��ֵ����bit.
	 *
	 * @param b the b
	 * @return the boolean array
	 */
	public static byte[] getBooleanArray(byte b) {
		byte[] array = new byte[8];
		for (int i = 7; i >= 0; i--) {
			array[i] = (byte) (b & 1);
			b = (byte) (b >> 1);
		}
		return array;
	}

	/**
	 *
	 * @param b the b
	 * @return the boolean array
	 */
	public static byte[] getBooleanArray(byte[] data, int startIndex, int length) {
		byte[] result = new byte[length];
		if (data.length < startIndex + length)
			return result;
		for (int i = 0; i < length; i++) {
			result[i] = data[startIndex + i];
		}
		return result;
	}

	/**
	 * ��ȡ������.
	 *
	 * @param num the num
	 * @param index ������һλ����ʼλ0
	 * @return the bit in int
	 */
	public static byte getBitInInt(int num, int index) {
		return (byte) ((num & (0x01 << index)) >> index);
	}

	/**
	 * ���鿽��
	 *
	 * @param memory the memory
	 * @param from the from
	 * @param to the to
	 * @return the byte[]
	 */
	public static byte[] copyOfRange(byte[] memory, int from, int to) {
		return Arrays.copyOfRange(memory, from, to);
	}

	/**
	 * ��ȡָ��λ�õ�byte
	 *
	 * @param memory the memory
	 * @param index the index
	 * @return the byte
	 */
	public static byte getByte(byte[] memory, int index) {
		return memory[index];
	}

	/**
	 * ��ȡ�޷��ŵ�λ
	 *
	 * @param memory the memory
	 * @param index the index
	 * @return the unsigned byte
	 */
	public static int getUnsignedByte(byte[] memory, int index) {
		return memory[index] & 0xff;
	}

	/**
	 * ָ��λ�û�ȡshort
	 *
	 * @param memory the memory
	 * @param index the index
	 * @return the short
	 */
	public static short getShort(byte[] memory, int index) {
		return (short) (memory[index] << 8 | memory[index + 1] & 0xFF);
	}

	/**
	 * ָ��λ�û�ȡ�޷��� short
	 *
	 * @param memory the memory
	 * @param index the index
	 * @return the unsigned short
	 */
	public static int getUnsignedShort(byte[] memory, int index) {
		return (memory[index] & 0xff) << 8 | memory[index + 1] & 0xFF;
	}

	/**
	 * С�� ��ȡshort
	 *
	 * @param memory the memory
	 * @param index the index
	 * @return the short LE
	 */
	public static short getShortLE(byte[] memory, int index) {
		return (short) (memory[index] & 0xff | memory[index + 1] << 8);
	}

	/**
	 * Gets the unsigned medium.
	 *
	 * @param memory the memory
	 * @param index the index
	 * @return the unsigned medium
	 */
	public static int getUnsignedMedium(byte[] memory, int index) {
		return (memory[index] & 0xff) << 16 | (memory[index + 1] & 0xff) << 8 | memory[index + 2] & 0xff;
	}

	/**
	 * Gets the unsigned medium LE.
	 *
	 * @param memory the memory
	 * @param index the index
	 * @return the unsigned medium LE
	 */
	public static int getUnsignedMediumLE(byte[] memory, int index) {
		return memory[index] & 0xff | (memory[index + 1] & 0xff) << 8 | (memory[index + 2] & 0xff) << 16;
	}

	/**
	 * ��ȡ����
	 *
	 * @param memory the memory
	 * @param index the index
	 * @return the int
	 */
	public static int getInt(byte[] memory, int index) {
		return (memory[index] & 0xff) << 24 | (memory[index + 1] & 0xff) << 16 | (memory[index + 2] & 0xff) << 8
				| memory[index + 3] & 0xff;
	}

	/**
	 * �޷�������
	 *
	 * @param memory the memory
	 * @param index the index
	 * @return the unsigned int
	 */
	public static long getUnsignedInt(byte[] memory, int index) {
		return ((long) memory[index] & 0xff) << 24 | ((long) memory[index + 1] & 0xff) << 16
				| ((long) memory[index + 2] & 0xff) << 8 | (long) memory[index + 3] & 0xff;
	}

	/**
	 * С������
	 *
	 * @param memory the memory
	 * @param index the index
	 * @return the int LE
	 */
	public static int getIntLE(byte[] memory, int index) {
		return memory[index] & 0xff | (memory[index + 1] & 0xff) << 8 | (memory[index + 2] & 0xff) << 16
				| (memory[index + 3] & 0xff) << 24;
	}

	/**
	 * Gets the long.
	 *
	 * @param memory the memory
	 * @param index the index
	 * @return the long
	 */
	public static long getLong(byte[] memory, int index) {
		return ((long) memory[index] & 0xff) << 56 | ((long) memory[index + 1] & 0xff) << 48
				| ((long) memory[index + 2] & 0xff) << 40 | ((long) memory[index + 3] & 0xff) << 32
				| ((long) memory[index + 4] & 0xff) << 24 | ((long) memory[index + 5] & 0xff) << 16
				| ((long) memory[index + 6] & 0xff) << 8 | (long) memory[index + 7] & 0xff;
	}

	/**
	 * Gets the long LE.
	 *
	 * @param memory the memory
	 * @param index the index
	 * @return the long LE
	 */
	public static long getLongLE(byte[] memory, int index) {
		return (long) memory[index] & 0xff | ((long) memory[index + 1] & 0xff) << 8
				| ((long) memory[index + 2] & 0xff) << 16 | ((long) memory[index + 3] & 0xff) << 24
				| ((long) memory[index + 4] & 0xff) << 32 | ((long) memory[index + 5] & 0xff) << 40
				| ((long) memory[index + 6] & 0xff) << 48 | ((long) memory[index + 7] & 0xff) << 56;
	}

	/**
	 * To bytes.
	 *
	 * @param string the string
	 * @return the byte[]
	 */
	public static byte[] toBytes(String string) {
		try {
			return string.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			return new byte[0];
		}
	}

	/**
	 * To string.
	 *
	 * @param bytes the bytes
	 * @return the string
	 */
	public static String toString(byte[] bytes) {
		String string = null;
		try {
			string = new String(bytes, "GBK").trim();
		} catch (UnsupportedEncodingException e) {
		}
		return string;
	}
	
	/**
	 * �ַ���ת�ɶ����ֽ� ���� GBK
	 * 
	 * @param string
	 * @param fixedLength
	 * @return
	 */
	public static byte[] toFixedBytes(String string, int fixedLength) {
		ByteBuffer buffer = ByteBuffer.allocate(fixedLength);
		try {
			buffer.put(string.getBytes("GBK"));
		} catch (UnsupportedEncodingException e) {
		}

		return buffer.array();
	}
	
	public static String hexDump(byte[] bytes) {
		return ByteBufUtil.hexDump(bytes);
	}
}
