package JT808Data.model.codec;


import java.util.Arrays;

import Utils.BcdCodeUtil;

/**
 * ÷’∂À ÷ª˙∫≈
 * 
 * @author cuillgln
 * 
 */
public class TerminalId {

	private  byte[] id = new byte[6];

	public TerminalId(byte[] bcdBytes) {
		id=Arrays.copyOf(bcdBytes, 6);
	}

	public static TerminalId createTerminalPhone(String idString) {
		char[] idca = idString.toCharArray();
		byte[] idba = new byte[idca.length / 2];
		for (int i = 0; i < idba.length; i++) {
			int b = idca[2 * i] - '0';
			b <<= 4;
			b += (idca[2 * i + 1] - '0');
			idba[i] = (byte) b;
		}
		return new TerminalId(idba);
	}

	public byte[] toByteArray() {
		return id;
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
		TerminalId other = (TerminalId) obj;
		if (!Arrays.equals(id, other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return BcdCodeUtil.bcdToString(id);
	}
}
