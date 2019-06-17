package JT809Data.netty.msghandler;

import JT809Data.model.Connection;
import JT809Data.model.InboundMessageHandler;
import JT809Data.model.JT809Constant;
import JT809Data.model.JT809Packet;

public class UpLinkTestRspHandler implements InboundMessageHandler {

	@Override
	public void handle(Connection session, JT809Packet packet) {

	}

	@Override
	public boolean canHandle(int msgId) {
		return JT809Constant.UP_LINKTEST_RSP == msgId;
	}
}
