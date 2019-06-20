
package JT808Data.model.codec;

/**
 * 附加信息
 * @author Administrator
 *
 */
public class NumberAttachment extends GnssAttachment {

	private final int byteLength;
	private final long attachmentValue;

	public NumberAttachment(int id, int byteLength, long attachmentValue) {
		super(id);
		this.byteLength = byteLength;
		this.attachmentValue = attachmentValue;
	}

	public int getByteLength() {
		return byteLength;
	}

	public long getAttachmentValue() {
		return attachmentValue;
	}
}
