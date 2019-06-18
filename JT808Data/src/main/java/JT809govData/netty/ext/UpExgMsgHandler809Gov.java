
package JT809govData.netty.ext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT809Data.model.JT809Constant;
import JT809govData.model.Connection809Gov;
import JT809govData.model.InboundMessageHandler809Gov;
import JT809govData.model.JT809GovPacket;
import Utils.ByteArrayUtil;

public class UpExgMsgHandler809Gov implements InboundMessageHandler809Gov {

	private static final Logger log = LoggerFactory.getLogger(UpExgMsgHandler809Gov.class);

	@Override
	public void handle(Connection809Gov session, JT809GovPacket packet) {
		byte[] body = packet.getMsgBody();
		String vehicleNo = ByteArrayUtil.toString(ByteArrayUtil.copyOfRange(body, 0, 21));
		int vehicleColor = ByteArrayUtil.getByte(body, 21);
		int dataType = ByteArrayUtil.getUnsignedShort(body, 22);
		long dataLength = ByteArrayUtil.getUnsignedInt(body, 24);
		byte[] data = ByteArrayUtil.copyOfRange(body, 28, body.length);
		if (dataLength != data.length) {
			log.warn("the DATA_LENGTH field value {} is not equals to the fact data length {}", dataLength,
					data.length);
		}

		UpExgMsgData809Gov mdata = new UpExgMsgData809Gov(packet.getMsgId(), packet.getGnssPlatformId(), vehicleNo,
				vehicleColor, dataType, data);
		handleUpExgMsgData(mdata);
	}

	@Override
	public boolean canHandle(int msgId) {
		return JT809Constant.UP_EXG_MSG == msgId;
	}

	private void handleUpExgMsgData(UpExgMsgData809Gov mdata) {
		UpExgMsgDataFactory809Gov.getInstance().buildMessage(mdata.getDataType()).handle(mdata);
	}

}
