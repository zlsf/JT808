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

/**
 * ��Ȩ
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

	int result = 0x00;// TODO�� �����Ȩ,ģ���¼�Ȩ�ɹ�
	if (result == JT808Constant.GENERAL_RSP_RESULT_SUCCESS) {
	    session.setTerminalId(packetData.getTerminalId());
	    session.setAuthenticated(true);
	    SessionManager.getInstance().addTerminal(packetData.getTerminalId(), session.getId());
	}
	// Ӧ��
	session.sendPlatformGeneralRsp(packetData.getTerminalId(), packetData.getMsgNo(), packetData.getMsgId(),
		result);
    }

}