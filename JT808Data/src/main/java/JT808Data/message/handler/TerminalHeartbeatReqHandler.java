
package JT808Data.message.handler;

import JT808Data.message.InboundMessageHandler;
import JT808Data.model.Session;
import JT808Data.model.codec.JT808Packet;
import Utils.JT808Constant;


/**
 * 心跳包处理
 *
 */
public class TerminalHeartbeatReqHandler implements InboundMessageHandler {

    public void handle(Session session, JT808Packet packetData) {
	session.sendPlatformGeneralRsp(packetData.getTerminalId(), packetData.getMsgNo(), packetData.getMsgId(),
		JT808Constant.GENERAL_RSP_RESULT_SUCCESS);
    }
}
