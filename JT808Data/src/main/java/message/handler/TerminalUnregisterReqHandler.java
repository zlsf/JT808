
package message.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import message.InboundMessageHandler;
import model.Session;
import model.codec.JT808Packet;

/**
 * ÖÕ¶Ë×¢Ïú
 * @author Administrator
 *
 */
public class TerminalUnregisterReqHandler implements InboundMessageHandler {

	private static final Logger log = LoggerFactory.getLogger(TerminalHeartbeatReqHandler.class);

	public void handle(Session session, JT808Packet packetData) {
		// ÖÕ¶Ë×¢Ïú
		int result = 0;
		session.sendPlatformGeneralRsp(packetData.getTerminalId(), packetData.getMsgNo(), packetData.getMsgId(),
				result);
	}

}
