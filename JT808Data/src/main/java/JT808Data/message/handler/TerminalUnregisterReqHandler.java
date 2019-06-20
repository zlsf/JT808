
package JT808Data.message.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT808Data.message.InboundMessageHandler;
import JT808Data.model.Session;
import JT808Data.model.codec.JT808Packet;


/**
 * 终端注销
 * @author Administrator
 *
 */
public class TerminalUnregisterReqHandler implements InboundMessageHandler {

	private static final Logger log = LoggerFactory.getLogger(TerminalHeartbeatReqHandler.class);

	public void handle(Session session, JT808Packet packetData) {
		// 终端注销
		int result = 0;
		session.sendPlatformGeneralRsp(packetData.getTerminalId(), packetData.getMsgNo(), packetData.getMsgId(),
				result);
	}

}
