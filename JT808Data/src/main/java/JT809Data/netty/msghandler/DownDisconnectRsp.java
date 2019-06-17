package JT809Data.netty.msghandler;

import JT809Data.model.JT809Constant;

public class DownDisconnectRsp extends AbstractOutboundMessage {

	public DownDisconnectRsp() {
		super(JT809Constant.DOWN_DISCONNECT_RSP);
	}

	@Override
	public byte[] getMsgBody() {
		return new byte[0];
	}

}
