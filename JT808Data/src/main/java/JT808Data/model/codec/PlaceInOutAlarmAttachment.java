
package JT808Data.model.codec;

import Utils.ByteArrayUtil;

/**
 * 报警附加信息
 * @author Administrator
 *
 */
public class PlaceInOutAlarmAttachment extends GnssAttachment {

	private PlaceInOut placeInOut;

	public PlaceInOutAlarmAttachment(int id, byte[] bytes) {
		super(id);
		int placeType = ByteArrayUtil.getUnsignedByte(bytes, 0);
		long placeId = ByteArrayUtil.getUnsignedInt(bytes, 1);
		int ioDirection = ByteArrayUtil.getUnsignedByte(bytes, 5);
		placeInOut = new PlaceInOut(placeType, placeId, ioDirection);
	}

	public PlaceInOut getPlaceInOut() {
		return placeInOut;
	}

	static class PlaceInOut {

		public static final int PLACE_TYPE_1 = 1;
		public static final int PLACE_TYPE_2 = 2;
		public static final int PLACE_TYPE_3 = 3;
		public static final int PLACE_TYPE_4 = 4;

		public static final int IO_DIRECTION_IN = 0;
		public static final int IO_DIRECTION_OUT = 1;

		private final int placeType;
		private final long placeId;
		private final int ioDirection;

		public PlaceInOut(int placeType, long placeId, int ioDirection) {
			this.placeType = placeType;
			this.placeId = placeId;
			this.ioDirection = ioDirection;
		}

		public int getPlaceType() {
			return placeType;
		}

		public long getPlaceId() {
			return placeId;
		}

		public int getIoDirection() {
			return ioDirection;
		}

	}
}
