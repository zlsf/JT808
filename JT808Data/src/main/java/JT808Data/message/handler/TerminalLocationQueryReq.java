
package JT808Data.message.handler;

import JT808Data.message.AbstractOutboundMessage;
import JT808Data.message.PlatformReqMessage;
import JT808Data.model.codec.TerminalId;
import Utils.JT808Constant;

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
