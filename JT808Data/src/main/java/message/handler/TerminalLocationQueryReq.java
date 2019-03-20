
package message.handler;

import Utils.JT808Constant;
import message.AbstractOutboundMessage;
import message.PlatformReqMessage;
import model.codec.TerminalId;

/**
 * 位置查询 基本永不上，如果需要手动查询的时候可以向设备发送
 * @author Administrator
 *
 */
public class TerminalLocationQueryReq extends AbstractOutboundMessage implements PlatformReqMessage {

	public TerminalLocationQueryReq(TerminalId terminalId) {
		super(JT808Constant.MSG_ID_TERMINAL_LOCATION_QUERY_REQ, terminalId);
	}

	@Override
	public byte[] getMsgBody() {
		// 协议里body信息为空
		return new byte[0];
	}
}
