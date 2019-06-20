package JT808Data.model.codec;

import Utils.ByteArrayUtil;

/**
 * 超速报警
 * 
 * @author cuillgln
 *
 */
public class PlaceOverspeedAlarmAttachment extends GnssAttachment {

	private PlaceOverspeed placeOverspeed;

	public PlaceOverspeedAlarmAttachment(int id, byte[] bytes) {
		super(id);
		int placeType = ByteArrayUtil.getUnsignedByte(bytes, 0);
		long placeId = 0;
		if (bytes.length > 1) {
			placeId = ByteArrayUtil.getUnsignedInt(bytes, 1);
		}
		placeOverspeed = new PlaceOverspeed(placeType, placeId);
	}

	public PlaceOverspeed getPlaceOverspeed() {
		return placeOverspeed;
	}

	static class PlaceOverspeed {

		public static final int PLACE_TYPE_0 = 0;
		public static final int PLACE_TYPE_1 = 1;
		public static final int PLACE_TYPE_2 = 2;
		public static final int PLACE_TYPE_3 = 3;
		public static final int PLACE_TYPE_4 = 4;

		private final int placeType;
		private final long placeId;

		public PlaceOverspeed(int placeType, long placeId) {
			this.placeType = placeType;
			this.placeId = placeId;
		}

		public int getPlaceType() {
			return placeType;
		}

		public long getPlaceId() {
			return placeId;
		}
	}
}
