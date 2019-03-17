
package message.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Utils.ByteArrayUtil;
import Utils.JT808Constant;
import message.InboundMessageHandler;
import model.Session;
import model.SessionManager;
import model.codec.JT808Packet;
import model.codec.TerminalAuthentication;
import model.codec.TerminalId;

/**
 * 鉴权
 * 
 * @author zlsf
 *
 */
public class TerminalAuthenticationReqHandler implements InboundMessageHandler {

	private static final Logger log = LoggerFactory.getLogger(TerminalHeartbeatReqHandler.class);

	@Override
	public void handle(Session session, JT808Packet packetData) {
		TerminalAuthentication auth = new TerminalAuthentication(packetData.getTerminalId().toString());
		auth.setAuthenticationKey(ByteArrayUtil.toString(packetData.getMsgBodyBytes()));
		log.info(auth.toString());

		int result = 0x00;// TODO： 这里鉴权,模拟下鉴权成功 鉴权信息存库

		if (result == JT808Constant.GENERAL_RSP_RESULT_SUCCESS) {
			session.setTerminalId(packetData.getTerminalId());
			session.setAuthenticated(true);
			SessionManager.getInstance().addTerminal(packetData.getTerminalId(), session.getId());

			log.error("鉴权成功。");
		}
		// 应答
		session.sendPlatformGeneralRsp(packetData.getTerminalId(), packetData.getMsgNo(), packetData.getMsgId(),
				result);
		// 鉴权完成以后询问驾驶员信息
		askDriverInfo(session, packetData.getTerminalId());

	}

	private void askDriverInfo(Session session, TerminalId terminalId) {
		try {
			Thread.sleep(1000);
			log.error("下发询问驾驶员。");
			DriverInfoReportReq req = new DriverInfoReportReq(terminalId);
			session.sendMessage(req, true);
		} catch (InterruptedException e) {
			log.error("下发询问驾驶员信息错误:{}", e);
		}
	}

}
