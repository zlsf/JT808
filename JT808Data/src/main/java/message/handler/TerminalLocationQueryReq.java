
package message.handler;

import Utils.JT808Constant;
import message.AbstractOutboundMessage;
import message.PlatformReqMessage;
import model.codec.TerminalId;

/**
 * λ�ò�ѯ ���������ϣ������Ҫ�ֶ���ѯ��ʱ��������豸����
 * @author Administrator
 *
 */
public class TerminalLocationQueryReq extends AbstractOutboundMessage implements PlatformReqMessage {

	public TerminalLocationQueryReq(TerminalId terminalId) {
		super(JT808Constant.MSG_ID_TERMINAL_LOCATION_QUERY_REQ, terminalId);
	}

	@Override
	public byte[] getMsgBody() {
		// Э����body��ϢΪ��
		return new byte[0];
	}
}
