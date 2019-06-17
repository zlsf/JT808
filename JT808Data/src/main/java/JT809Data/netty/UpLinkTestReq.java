package JT809Data.netty;

import JT809Data.model.JT809Constant;
import JT809Data.netty.msghandler.AbstractOutboundMessage;

public class UpLinkTestReq extends AbstractOutboundMessage {

	public UpLinkTestReq() {
		super(JT809Constant.UP_LINKTEST_REQ);
	}

	@Override
	public byte[] getMsgBody() {
		return new byte[0];
	}

}
