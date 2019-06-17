package JT809Data.netty.msghandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT809Data.model.Connection;
import JT809Data.model.JT809Constant;
import JT809Data.model.JT809Packet;
import Utils.ByteArrayUtil;


public class DownDisconnectInformHandler implements JT809Data.model.InboundMessageHandler {
	private static final Logger log = LoggerFactory.getLogger(DownDisconnectInformHandler.class);

	@Override
	public void handle(Connection session, JT809Packet packet) {
		int errorCode = ByteArrayUtil.getUnsignedByte(packet.getMsgBody(), 0);
		log.info("received down disconnect info with errorCode: {}", errorCode);
	}

	@Override
	public boolean canHandle(int msgId) {
		return JT809Constant.DOWN_DISCONNECT_INFORM == msgId;
	}
}
