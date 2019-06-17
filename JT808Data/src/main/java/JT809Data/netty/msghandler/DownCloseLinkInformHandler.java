
package JT809Data.netty.msghandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT809Data.model.Connection;
import JT809Data.model.InboundMessageHandler;
import JT809Data.model.JT809Constant;
import JT809Data.model.JT809Packet;
import Utils.ByteArrayUtil;

public class DownCloseLinkInformHandler implements InboundMessageHandler {

	private static final Logger log = LoggerFactory.getLogger(DownCloseLinkInformHandler.class);

	@Override
	public void handle(Connection session, JT809Packet packet) {
		int reasonCode = ByteArrayUtil.getUnsignedByte(packet.getMsgBody(), 0);
		log.info("received down close link info with reasonCode: {}", reasonCode);
	}

	@Override
	public boolean canHandle(int msgId) {
		return JT809Constant.DOWN_CLOSELINK_INFORM == msgId;
	}
}
