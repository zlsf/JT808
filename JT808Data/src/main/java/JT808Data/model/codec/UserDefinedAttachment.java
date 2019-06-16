package JT808Data.model.codec;

import Utils.ByteArrayUtil;

public class UserDefinedAttachment extends GnssAttachment {

	private String info;

	public UserDefinedAttachment(int id, byte[] bytes) {
		super(id);
		info = ByteArrayUtil.toString(bytes);
	}

	public String getInfo() {
		return info;
	}

}
