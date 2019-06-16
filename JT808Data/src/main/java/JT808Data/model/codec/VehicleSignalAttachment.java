
package JT808Data.model.codec;

import Utils.ByteArrayUtil;

/**
 * 车辆附加信息
 * @author Administrator
 *
 */
public class VehicleSignalAttachment extends GnssAttachment {

	private VehicleSignal vehicleSignal;

	public VehicleSignalAttachment(int id, byte[] bytes) {
		super(id);
		vehicleSignal = new VehicleSignal(ByteArrayUtil.getInt(bytes, 0));
	}

	public VehicleSignal getVehicleSignal() {
		return vehicleSignal;
	}
}
