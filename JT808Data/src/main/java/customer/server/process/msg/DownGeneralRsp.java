
package customer.server.process.msg;

import java.nio.ByteBuffer;

import com.kj.datacenter.core.ProtocolConstant;

import customer.server.model.DeviceId;

public class DownGeneralRsp extends AbstractMessage {

    private int ackMsgNo;
    private int ackMsgId;
    private int result;

    public DownGeneralRsp(DeviceId deviceId) {
	super(ProtocolConstant.MSG_ID_DOWN_GENERAL_RSP, deviceId);
    }

    @Override
    public void setMsgBody(byte[] body) {
	ByteBuffer buffer = ByteBuffer.wrap(body);
	ackMsgNo = buffer.getShort() & 0xFFFF;
	ackMsgId = buffer.getShort() & 0xFFFF;
	result = buffer.get() & 0xFF;
    }

    @Override
    public byte[] getMsgBody() {
	ByteBuffer buffer = ByteBuffer.allocate(5);
	buffer.putShort((short) ackMsgNo);
	buffer.putShort((short) ackMsgId);
	buffer.put((byte) result);
	return buffer.array();
    }

    public void setAckMsgNo(int ackMsgNo) {
	this.ackMsgNo = ackMsgNo;
    }

    public void setAckMsgId(int ackMsgId) {
	this.ackMsgId = ackMsgId;
    }

    public void setResult(int result) {
	this.result = result;
    }

}
