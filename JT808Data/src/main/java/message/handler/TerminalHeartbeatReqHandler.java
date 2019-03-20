
package message.handler;

import Utils.JT808Constant;
import message.InboundMessageHandler;
import model.Session;
import model.codec.JT808Packet;

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
