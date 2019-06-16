
package JT808Data.message.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT808Data.message.InboundMessageHandler;
import JT808Data.model.Session;
import JT808Data.model.SessionManager;
import JT808Data.model.codec.JT808Packet;
import JT808Data.model.codec.TerminalAuthentication;
import JT808Data.model.codec.TerminalId;
import Utils.ByteArrayUtil;
import Utils.JT808Constant;

/**
 * ��Ȩ
 * 
 * @author zlsf
 *
 */
public class TerminalAuthenticationReqHandler implements InboundMessageHandler {

	private static final Logger log = LoggerFactory.getLogger(TerminalAuthenticationReqHandler.class);

	@Override
	public void handle(Session session, JT808Packet packetData) {
		TerminalAuthentication auth = new TerminalAuthentication(packetData.getTerminalId().toString());
		auth.setAuthenticationKey(ByteArrayUtil.toString(packetData.getMsgBodyBytes()));
		log.info(auth.toString());

		int result = 0x00;// TODO�� �����Ȩ,ģ���¼�Ȩ�ɹ� ��Ȩ��Ϣ���

		if (result == JT808Constant.GENERAL_RSP_RESULT_SUCCESS) {
			session.setTerminalId(packetData.getTerminalId());
			session.setAuthenticated(true);
			SessionManager.getInstance().addTerminal(packetData.getTerminalId(), session.getId());

			log.info("��Ȩ�ɹ���");
		}
		// Ӧ��
		session.sendPlatformGeneralRsp(packetData.getTerminalId(), packetData.getMsgNo(), packetData.getMsgId(),result);
		// ��Ȩ����Ժ�ѯ�ʼ�ʻԱ��Ϣ
		askDriverInfo(session, packetData.getTerminalId());

	}

	private void askDriverInfo(Session session, TerminalId terminalId) {
		try {
			Thread.sleep(1000);
			log.error("�·�ѯ�ʼ�ʻԱ��");
			DriverInfoReportReq req = new DriverInfoReportReq(terminalId);
			session.sendMessage(req, true);
		} catch (InterruptedException e) {
			log.error("�·�ѯ�ʼ�ʻԱ��Ϣ����:{}", e);
		}
	}

}
