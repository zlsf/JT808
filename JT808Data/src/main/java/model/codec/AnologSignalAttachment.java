package model.codec;

import Utils.ByteArrayUtil;

public class AnologSignalAttachment extends GnssAttachment {

	private int ad1;
	private int ad0;

	public AnologSignalAttachment(int id, byte[] bytes) {
		super(id);
		ad1 = ByteArrayUtil.getShort(bytes, 0);
		ad0 = ByteArrayUtil.getShort(bytes, 2);
	}

	public int getAd1() {
		return ad1;
	}

	public int getAd0() {
		return ad0;
	}

}
