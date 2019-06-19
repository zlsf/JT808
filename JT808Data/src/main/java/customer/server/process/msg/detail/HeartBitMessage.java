
package customer.server.process.msg.detail;

import java.nio.ByteBuffer;

import Utils.ByteArrayUtil;
import customer.server.model.PacketData;
import customer.server.model.Session;
import customer.server.model.SessionManager;
import customer.server.process.MessageSender;
import customer.server.process.msg.AbstractMessage;

public class HeartBitMessage extends AbstractMessage {

    private String version;

    public HeartBitMessage(PacketData data) {
	super(data);
    }

    public void setMsgBody(byte[] body) {
	ByteBuffer buffer = ByteBuffer.wrap(body);
	int length = buffer.get();
	byte[] bytes = new byte[length];
	buffer.get(bytes);
	version = ByteArrayUtil.toString(bytes);
    }

    public byte[] getMsgBody() {
	byte[] content = ByteArrayUtil.toBytes(version);
	ByteBuffer buffer = ByteBuffer.allocate(content.length + 1);
	buffer.put((byte) content.length);
	buffer.put(content);
	return buffer.array();
    }

    @Override
    public void dealMessage() {
	String sessionId = Session.buildId(this.channel);
	Session session = SessionManager.getInstance().findBySessionId(sessionId);
	if (session == null) {
	    session = Session.buildSession(this.channel);
	}
	session.setDeviceId(this.deviceId);
	SessionManager.getInstance().put(sessionId, session);

	try {
	    MessageSender.getInstance().sendDownGeneralRspMsg(this);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Override
    public String toString() {
	return "HeartBit [version=" + version + ", msgId=" + msgId + ", deviceId=" + deviceId + ", msgNo=" + msgNo
		+ ", channel=" + channel + ", deviceCode=" + deviceCode + "]";
    }
}
