package JT809Data.netty.msghandler;




import JT809Data.model.Connection;
import JT809Data.model.InboundMessageHandler;
import JT809Data.model.JT809Constant;
import JT809Data.model.JT809Packet;
import JT809Data.netty.JT809ConnectionManager;
import Utils.ByteArrayUtil;


public class DownConnectReqHandler implements InboundMessageHandler {


	private JT809ConnectionManager connectionManager=new JT809ConnectionManager();

	@Override
	public void handle(Connection conn, JT809Packet packet) {
		long verifyCode = ByteArrayUtil.getUnsignedInt(packet.getMsgBody(), 0);
		long storedVerifyCode = connectionManager.getVerifyCode();
		DownConnectRsp rsp = new DownConnectRsp();
		if (verifyCode == storedVerifyCode) {
			conn.setAuthenticated(true);
			connectionManager.setDownlinkConnection(conn);
			rsp.setResult(DownConnectRsp.RESULT_SUCCESS);
		} else {
			rsp.setResult(DownConnectRsp.RESULT_ERROR);
		}
		conn.sendMessage(rsp);
	}

	@Override
	public boolean canHandle(int msgId) {
		return JT809Constant.DOWN_CONNECT_REQ == msgId;
	}
}
