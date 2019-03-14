package message.handler;

import java.nio.ByteBuffer;

import Utils.ByteArrayUtil;
import Utils.JT808Constant;
import message.AbstractOutboundMessage;
import message.PlatformAckMessage;
import model.codec.TerminalId;
import model.codec.TerminalRegisterResult;

/**
 * ÖÕ¶Ë×¢²áÓ¦´ð
 * 
 * @author zlsf
 *
 */
public class TerminalRegisterRsp extends AbstractOutboundMessage implements PlatformAckMessage {

    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_VEHICLE_ALREADY_EXISTS = 1;
    public static final int RESULT_VEHICLE_NOT_EXISTS = 2;
    public static final int RESULT_TERMINAL_ALREADY_EXISTS = 3;
    public static final int RESULT_TERMINAL_NOT_EXISTS = 4;

    private int ackMsgNo;
    private TerminalRegisterResult result;

    public TerminalRegisterRsp(TerminalId terminalPhone) {
	super(JT808Constant.MSG_ID_TERMINAL_REGISTER_RSP, terminalPhone);
    }

    @Override
    public byte[] getMsgBody() {
	int result = this.result.getResult();
	byte[] auth = ByteArrayUtil.toBytes(this.result.getAuthenticationKey());
	ByteBuffer buffer = ByteBuffer.allocate(3 + auth.length);
	buffer.putShort((short) ackMsgNo);
	buffer.put((byte) result);
	if (result == 0) {
	    buffer.put(auth);
	}
	return buffer.array();
    }

    public int getAckMsgNo() {
	return ackMsgNo;
    }

    @Override
    public void setAckMsgNo(int ackMsgNo) {
	this.ackMsgNo = ackMsgNo;
    }

    @Override
    public void setAckMsgId(int ackMsgId) {
    }

    public TerminalRegisterResult getResult() {
	return result;
    }

    public void setResult(TerminalRegisterResult result) {
	this.result = result;
    }
}
