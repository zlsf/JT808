
package JT809Data.netty.msghandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT809Data.model.Connection;
import JT809Data.model.InboundMessageHandler;
import JT809Data.model.JT809Constant;
import JT809Data.model.JT809Packet;
import JT809Data.netty.JT809ConnectionManager;

public class UpDisconnectRspHandler implements InboundMessageHandler {

	private static final Logger log = LoggerFactory.getLogger(UpDisconnectRspHandler.class);

	private JT809ConnectionManager connectionManager = new JT809ConnectionManager();

	@Override
	public void handle(Connection conn, JT809Packet packet) {
		log.info("received UP_DISCONNECT_RSP, close the connection");
		connectionManager.setMainlinkConnection(null);
		conn.setAuthenticated(false);
		conn.close();
	}

	@Override
	public boolean canHandle(int msgId) {
		return JT809Constant.UP_DISCONNECT_RSP == msgId;
	}

}
