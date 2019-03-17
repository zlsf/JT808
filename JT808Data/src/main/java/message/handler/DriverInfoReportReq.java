
package message.handler;

import Utils.JT808Constant;
import message.AbstractOutboundMessage;
import message.PlatformReqMessage;
import model.codec.TerminalId;

/**
 * ѯ�ʼ�ʻԱ��Ϣ
 * @author Administrator
 *
 */
public class DriverInfoReportReq extends AbstractOutboundMessage implements PlatformReqMessage {

	public DriverInfoReportReq(TerminalId terminalId) {
		super(JT808Constant.MSG_ID_PLATFORM_DRIVER_REPORT_REQ, terminalId);
	}

	@Override
	public byte[] getMsgBody() {
		return new byte[0];
	}

}
