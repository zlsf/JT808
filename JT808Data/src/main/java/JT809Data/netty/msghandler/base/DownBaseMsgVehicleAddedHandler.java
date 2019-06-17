
package JT809Data.netty.msghandler.base;

import JT809Data.model.JT809Constant;

public class DownBaseMsgVehicleAddedHandler implements DownBaseMsgDataHandler {

	@Override
	public void handle(DownBaseMsgData mdata) {

	}

	@Override
	public boolean canHandle(int dataType) {
		return JT809Constant.DOWN_BASE_MSG_VEHICLE_ADDED == dataType;
	}

}
