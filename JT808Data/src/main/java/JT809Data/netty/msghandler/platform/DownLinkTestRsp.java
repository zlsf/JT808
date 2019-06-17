
package JT809Data.netty.msghandler.platform;

import JT809Data.model.JT809Constant;
import JT809Data.netty.msghandler.AbstractOutboundMessage;

public class DownLinkTestRsp extends AbstractOutboundMessage {

	public DownLinkTestRsp() {
		super(JT809Constant.DOWN_LINKTEST_RSP);
	}

	@Override
	public byte[] getMsgBody() {
		return new byte[0];
	}

}
