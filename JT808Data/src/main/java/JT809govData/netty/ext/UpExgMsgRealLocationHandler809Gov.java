
package JT809govData.netty.ext;


import JT809Data.model.JT809Constant;
import JT809govData.model.exg.Position809Gov;

public class UpExgMsgRealLocationHandler809Gov implements UpExgMsgDataHandler809Gov {

	@Override
	public void handle(UpExgMsgData809Gov mData) {
		byte[] pdata = mData.getDataBytes();
		Position809Gov p = Position809Gov.restoreFromByteArray(mData.getGnssPlatformId().getId(), mData.getVehicleNo(),
				mData.getVehicleColor(), pdata);
		System.out.println(p.toString());
	
	}

	@Override
	public boolean canHandle(int dataType) {
		return JT809Constant.UP_EXG_MSG_REAL_LOCATION == dataType;
	}
}
