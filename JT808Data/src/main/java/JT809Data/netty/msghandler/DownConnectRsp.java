package JT809Data.netty.msghandler;

import JT809Data.model.JT809Constant;

public class DownConnectRsp extends AbstractOutboundMessage {
	
	public static final int RESULT_SUCCESS = 0x00;
	public static final int RESULT_ERROR = 0x01;
	public static final int RESULT_RESOURCE_ERROR = 0x02;
	public static final int RESULT_OTHER_ERROR = 0x03;

	private int result;

	public DownConnectRsp() {
		super(JT809Constant.DOWN_CONNECT_RSP);
	}

	@Override
	public byte[] getMsgBody() {
		return new byte[] { (byte) result };
	}

	public void setResult(int result) {
		this.result = result;
	}

}
