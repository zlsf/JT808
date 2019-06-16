package JT808Data.message;

import java.nio.ByteBuffer;

import JT808Data.model.codec.TerminalId;
import Utils.JT808Constant;


public class PlatformGeneralRsp extends AbstractOutboundMessage implements PlatformAckMessage {

	private int ackMsgNo;
	private int ackMsgId;
	private int result;

	public PlatformGeneralRsp(TerminalId terminalPhone) {
		super(JT808Constant.MSG_ID_PLATFORM_GENERAL_RSP, terminalPhone);
	}

	@Override
	public byte[] getMsgBody() {
		ByteBuffer buffer = ByteBuffer.allocate(5);
		buffer.putShort((short) ackMsgNo);
		buffer.putShort((short) ackMsgId);
		buffer.put((byte) result);
		return buffer.array();
	}

	public int getAckMsgNo() {
		return ackMsgNo;
	}

	public void setAckMsgNo(int ackMsgNo) {
		this.ackMsgNo = ackMsgNo;
	}

	public int getAckMsgId() {
		return ackMsgId;
	}

	public void setAckMsgId(int ackMsgId) {
		this.ackMsgId = ackMsgId;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}
