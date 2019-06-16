
package JT808Data.message.handler;

import JT808Data.message.AbstractOutboundMessage;
import JT808Data.message.PlatformReqMessage;
import JT808Data.model.codec.TerminalId;
import Utils.JT808Constant;


/**
 * 询问驾驶员信息
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
