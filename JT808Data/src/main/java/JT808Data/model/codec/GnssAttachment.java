package JT808Data.model.codec;

import java.util.ArrayList;
import java.util.List;

import Utils.ByteArrayUtil;

/**
 * 定位附加信息
 * 
 * @author cuillgln
 *
 */
public abstract class GnssAttachment {
	public static final int ATTACHEMENT_0x01 = 0x01;
	public static final int ATTACHEMENT_0x02 = 0x02;
	public static final int ATTACHEMENT_0x03 = 0x03;
	public static final int ATTACHEMENT_0x04 = 0x04;

	public static final int ATTACHEMENT_0x11 = 0x11;
	public static final int ATTACHEMENT_0x12 = 0x12;
	public static final int ATTACHEMENT_0x13 = 0x13;

	public static final int ATTACHEMENT_0x25 = 0x25;
	public static final int ATTACHEMENT_0x2A = 0x2A;
	public static final int ATTACHEMENT_0x2B = 0x2B;

	public static final int ATTACHEMENT_0x30 = 0x30;
	public static final int ATTACHEMENT_0x31 = 0x31;
	public static final int ATTACHEMENT_0xE0 = 0xE0;

	protected final int id;

	public GnssAttachment(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	/**
	 * 生成附件
	 * 
	 * @param bytes
	 * @return
	 */
	public static List<GnssAttachment> restoreAttachments(byte[] bytes) {
		List<GnssAttachment> attachments = new ArrayList<>();
		int index = 0;
		while (index < bytes.length) {
			int attachmentId = ByteArrayUtil.getUnsignedByte(bytes, index);
			int attachmentLength = ByteArrayUtil.getUnsignedByte(bytes, index + 1);
			byte[] attachmentBytes = ByteArrayUtil.copyOfRange(bytes, index + 2, index + 2 + attachmentLength);
			attachments.add(restoreAttachment(attachmentId, attachmentBytes));
			index += (2 + attachmentLength);
		}
		return attachments;
	}

	private static GnssAttachment restoreAttachment(int attachmentId, byte[] attachmentBytes) {
		switch (attachmentId) {
		case GnssAttachment.ATTACHEMENT_0x01:
			return new NumberAttachment(attachmentId, 4, ByteArrayUtil.getUnsignedInt(attachmentBytes, 0));
		case GnssAttachment.ATTACHEMENT_0x02:
		case GnssAttachment.ATTACHEMENT_0x03:
		case GnssAttachment.ATTACHEMENT_0x04:
			return new NumberAttachment(attachmentId, 2, ByteArrayUtil.getUnsignedShort(attachmentBytes, 0));
		case GnssAttachment.ATTACHEMENT_0x30:
		case GnssAttachment.ATTACHEMENT_0x31:
			return new NumberAttachment(attachmentId, 1, ByteArrayUtil.getUnsignedByte(attachmentBytes, 0));
		case GnssAttachment.ATTACHEMENT_0x11:
			return new PlaceOverspeedAlarmAttachment(attachmentId, attachmentBytes);
		case GnssAttachment.ATTACHEMENT_0x12:
			return new PlaceInOutAlarmAttachment(attachmentId, attachmentBytes);
		case GnssAttachment.ATTACHEMENT_0x13:
			return new RoadSectionDriveDurationAlarmAttachment(attachmentId, attachmentBytes);
		case GnssAttachment.ATTACHEMENT_0x25:
			return new VehicleSignalAttachment(attachmentId, attachmentBytes);
		case GnssAttachment.ATTACHEMENT_0x2A:
			return new IOSignalAttachment(attachmentId, attachmentBytes);
		case GnssAttachment.ATTACHEMENT_0x2B:
			return new AnologSignalAttachment(attachmentId, attachmentBytes);
		case GnssAttachment.ATTACHEMENT_0xE0:
			return new UserDefinedAttachment(attachmentId, attachmentBytes);
		default:
			return null;
		}
	}
}
