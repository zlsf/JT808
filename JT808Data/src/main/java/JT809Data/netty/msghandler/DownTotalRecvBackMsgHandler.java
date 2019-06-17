package JT809Data.netty.msghandler;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT809Data.model.Connection;
import JT809Data.model.InboundMessageHandler;
import JT809Data.model.JT809Constant;
import JT809Data.model.JT809Packet;
import Utils.ByteArrayUtil;

public class DownTotalRecvBackMsgHandler implements InboundMessageHandler {

	private static final Logger log = LoggerFactory.getLogger(DownTotalRecvBackMsgHandler.class);

	@Override
	public void handle(Connection session, JT809Packet packet) {
		byte[] msgBody = packet.getMsgBody();
		long dynamicInfoTotal = ByteArrayUtil.getUnsignedInt(msgBody, 0);
		Date startTime = new Date(ByteArrayUtil.getLong(msgBody, 4) * 1000);
		Date endTime = new Date(ByteArrayUtil.getLong(msgBody, 12) * 1000);
		log.info("received down total recv back msg, total recv {} from {} to {}", dynamicInfoTotal, startTime,
				endTime);
	}

	@Override
	public boolean canHandle(int msgId) {
		return JT809Constant.DOWN_TOTAL_RECV_BACK_MSG == msgId;
	}
}
