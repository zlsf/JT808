
package JT809Data.netty.msghandler.platform;

import JT809Data.model.JT809Constant;
import Utils.ByteArrayUtil;

public class DownPlatformMsgInfoReqHandler implements DownPlatformMsgDataHandler {

	private PlatformManagerService platformManagerService = new PlatformManagerService();;

	@Override
	public void handle(DownPlatformMsgData mdata) {
		byte[] data = mdata.getDataBytes();
		long infoId = ByteArrayUtil.getUnsignedInt(data, 0);
		String info = ByteArrayUtil.toString(ByteArrayUtil.copyOfRange(data, 8, data.length));
		platformManagerService.ackInfo(infoId, info);
	}

	@Override
	public boolean canHandle(int dataType) {
		return JT809Constant.DOWN_PLATFORM_MSG_INFO_REQ == dataType;
	}
}
