package JT808Data.model.codec;

import Utils.ByteArrayUtil;

public class RoadSectionDriveDurationAlarmAttachment extends GnssAttachment {

	private RoadSectionDriveDuration segmentDriveDuration;

	public RoadSectionDriveDurationAlarmAttachment(int id, byte[] bytes) {
		super(id);
		long segmentId = ByteArrayUtil.getUnsignedInt(bytes, 0);
		int driveDuration = ByteArrayUtil.getUnsignedShort(bytes, 4);
		int result = ByteArrayUtil.getUnsignedByte(bytes, 5);
		segmentDriveDuration = new RoadSectionDriveDuration(segmentId, driveDuration, result);
	}

	public RoadSectionDriveDuration getSegmentDriveDuration() {
		return segmentDriveDuration;
	}

	public class RoadSectionDriveDuration {

		public static final int RESULT_TOO_SHORT = 0;
		public static final int RESULT_TOO_LONG = 1;

		private final long sectionId;
		private final int driveDuration;
		private final int result;

		public RoadSectionDriveDuration(long segmentId, int driveDuration, int result) {
			this.sectionId = segmentId;
			this.driveDuration = driveDuration;
			this.result = result;
		}

		public long getSegmentId() {
			return sectionId;
		}

		public int getDriveDuration() {
			return driveDuration;
		}

		public int getResult() {
			return result;
		}

	}
}
