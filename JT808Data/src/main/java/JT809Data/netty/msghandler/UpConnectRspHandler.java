
package JT809Data.netty.msghandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT809Data.model.Connection;
import JT809Data.model.InboundMessageHandler;
import JT809Data.model.JT809Constant;
import JT809Data.model.JT809Packet;
import JT809Data.netty.JT809ConnectionManager;
import Utils.ByteArrayUtil;

public class UpConnectRspHandler implements InboundMessageHandler {

	private static final Logger log = LoggerFactory.getLogger(UpConnectRspHandler.class);

	private JT809ConnectionManager connectionManager = new JT809ConnectionManager();

	@Override
	public void handle(Connection conn, JT809Packet packet) {
		byte[] msgBodyBytes = packet.getMsgBody();
		int result = ByteArrayUtil.getUnsignedByte(msgBodyBytes, 0);
		if (result == 0x00) {
			long verifyCode = ByteArrayUtil.getUnsignedInt(msgBodyBytes, 1);
			connectionManager.setVerifyCode(verifyCode);
			conn.setAuthenticated(true);
			connectionManager.setMainlinkConnection(conn);
		} else {
			log.info("received UP_CONNECT_RSP with result: 0x" + Integer.toHexString(result));
		}
	}

	@Override
	public boolean canHandle(int msgId) {
		return JT809Constant.UP_CONNECT_RSP == msgId;
	}
}
