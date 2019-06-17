
package JT809Data.netty.msghandler;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import JT809Data.model.Connection;
import JT809Data.model.InboundMessageHandler;
import JT809Data.model.JT809Constant;
import JT809Data.model.JT809Packet;
import JT809Data.netty.msghandler.base.DownBaseMsgData;
import JT809Data.netty.msghandler.base.DownBaseMsgDataHandler;
import Utils.ByteArrayUtil;

public class DownBaseMsgHandler implements InboundMessageHandler {

	private static final Logger log = LoggerFactory.getLogger(DownBaseMsgHandler.class);

	private Set<DownBaseMsgDataHandler> handlers;

	@Override
	public void handle(Connection session, JT809Packet packet) {
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
		DownBaseMsgData mdata = new DownBaseMsgData(packet.getMsgId(), packet.getGnssPlatformId(), vehicleNo,
				vehicleColor, dataType, data);
		boolean handled = false;
		for (DownBaseMsgDataHandler handler : handlers) {
			if (handler.canHandle(dataType)) {
				handled = true;
				handler.handle(mdata);
			}
		}
		if (!handled) {
			log.info("message handler for DOWN_BASE_MSG data type 0x{} is not implemented",
					Integer.toHexString(dataType));
		}
	}

	@Override
	public boolean canHandle(int msgId) {
		return JT809Constant.DOWN_BASE_MSG == msgId;
	}

}
