package JT809Data.netty.msghandler;


import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT809Data.model.Connection;
import JT809Data.model.JT809Constant;
import JT809Data.model.JT809Packet;
import JT809Data.netty.msghandler.platform.DownPlatformMsgData;
import JT809Data.netty.msghandler.platform.DownPlatformMsgDataHandler;
import Utils.ByteArrayUtil;


public class DownPlatformMsgHandler implements JT809Data.model.InboundMessageHandler {
	private static final Logger log = LoggerFactory.getLogger(DownPlatformMsgHandler.class);


	private Set<DownPlatformMsgDataHandler> handlers;

	@Override
	public void handle(Connection session, JT809Packet packet) {
		byte[] body = packet.getMsgBody();
		int dataType = ByteArrayUtil.getUnsignedShort(body, 0);
		long dataLength = ByteArrayUtil.getUnsignedInt(body, 2);
		byte[] data = ByteArrayUtil.copyOfRange(body, 6, body.length);
		if (dataLength != data.length) {
			log.warn("the DATA_LENGTH field value {} is not equals to the fact data length {}", dataLength, data.length);
		}
		DownPlatformMsgData mdata = new DownPlatformMsgData(packet.getMsgId(), packet.getGnssPlatformId(), dataType, data);
		boolean handled = false;
		for (DownPlatformMsgDataHandler handler : handlers) {
			if (handler.canHandle(dataType)) {
				handled = true;
				handler.handle(mdata);
			}
		}
		if (!handled) {
			log.info("message handler for DOWN_PLATFORM_MSG data type 0x{} is not implemented", Integer.toHexString(dataType));
		}
	}

	@Override
	public boolean canHandle(int msgId) {
		return JT809Constant.DOWN_PLATFORM_MSG == msgId;
	}
}
