package JT809Data.netty.msghandler;

import java.nio.ByteBuffer;


import JT809Data.model.JT809Constant;
import Utils.ByteArrayUtil;

public class UpConnectReq extends AbstractOutboundMessage {

	private long userId;
	private String password;
	private String downLinkHost;
	private int downLinkPort;

	public UpConnectReq() {
		super(JT809Constant.UP_CONNECT_REQ);
	}

	@Override
	public byte[] getMsgBody() {
		ByteBuffer buffer = ByteBuffer.allocate(46);
		buffer.putInt((int) userId);
		buffer.put(ByteArrayUtil.toFixedBytes(password, 8));
		buffer.put(ByteArrayUtil.toFixedBytes(downLinkHost, 32));
		buffer.putShort((short) downLinkPort);
		return buffer.array();
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDownLinkHost(String downLinkHost) {
		this.downLinkHost = downLinkHost;
	}

	public void setDownLinkPort(int downLinkPort) {
		this.downLinkPort = downLinkPort;
	}


}
