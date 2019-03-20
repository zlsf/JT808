
package model.codec;

import Utils.ByteArrayUtil;

/**
 * ¸½¼ÓÐÅÏ¢
 * @author Administrator
 *
 */
public class IOSignalAttachment extends GnssAttachment {

	private IOSignal ioSignal;

	public IOSignalAttachment(int id, byte[] bytes) {
		super(id);
		ioSignal = new IOSignal(ByteArrayUtil.getUnsignedShort(bytes, 0));
	}

	public IOSignal getIoSignal() {
		return ioSignal;
	}

	static class IOSignal {

		private int deeperSleepSignal;
		private int hibernateSignal;

		public IOSignal(int bits) {
			deeperSleepSignal = bits & 0x01;
			hibernateSignal = bits >> 1 & 0x01;
		}

		public int getDeeperSleepSignal() {
			return deeperSleepSignal;
		}

		public void setDeeperSleepSignal(int deeperSleepSignal) {
			this.deeperSleepSignal = deeperSleepSignal;
		}

		public int getHibernateSignal() {
			return hibernateSignal;
		}

		public void setHibernateSignal(int hibernateSignal) {
			this.hibernateSignal = hibernateSignal;
		}

	}

}
