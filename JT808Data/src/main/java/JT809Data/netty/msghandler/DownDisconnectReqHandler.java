
package JT809Data.netty.msghandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT809Data.model.Connection;
import JT809Data.model.InboundMessageHandler;
import JT809Data.model.JT809Constant;
import JT809Data.model.JT809Packet;
import JT809Data.netty.JT809ConnectionManager;
import Utils.ByteArrayUtil;

public class DownDisconnectReqHandler implements InboundMessageHandler {

	private static final Logger log = LoggerFactory.getLogger(DownDisconnectReqHandler.class);

	private JT809ConnectionManager connectionManager = new JT809ConnectionManager();

	@Override
	public void handle(Connection conn, JT809Packet packet) {
		log.info("recevied DOWN_DISCONNECT_RSP");
		long verifyCode = ByteArrayUtil.getUnsignedInt(packet.getMsgBody(), 0);
		long storedVerifyCode = connectionManager.getVerifyCode();
		if (verifyCode == storedVerifyCode) {
			DownDisconnectRsp rsp = new DownDisconnectRsp();
			conn.sendMessage(rsp);
		}
		connectionManager.setDownlinkConnection(null);
		conn.setAuthenticated(false);
	}

	@Override
	public boolean canHandle(int msgId) {
		return JT809Constant.DOWN_DISCONNECT_REQ == msgId;
	}
}
