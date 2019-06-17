
package JT809Data.netty.msghandler.platform;

import JT809Data.model.Connection;
import JT809Data.model.InboundMessageHandler;
import JT809Data.model.JT809Constant;
import JT809Data.model.JT809Packet;

public class DownLinkTestReqHandler implements InboundMessageHandler {

	@Override
	public void handle(Connection conn, JT809Packet packet) {
		DownLinkTestRsp rsp = new DownLinkTestRsp();
		conn.sendMessage(rsp);
	}

	@Override
	public boolean canHandle(int msgId) {
		return JT809Constant.DOWN_LINKTEST_REQ == msgId;
	}
}
