
package customer.server.process.msg.detail;

import java.nio.ByteBuffer;

import Utils.ByteArrayUtil;
import customer.server.model.PackData;
import customer.server.model.Session;
import customer.server.model.SessionManager;
import customer.server.process.MessageSender;
import customer.server.process.msg.AbstractMessage;

public class HeartBitMessage extends AbstractMessage {

    public HeartBitMessage(PackData data) {
	super(data);
    }

    @Override
    public void dealMsg() {
	String sessionId = Session.buildId(this.data.getChannel());
	Session session = SessionManager.getInstance().findBySessionId(sessionId);
	if (session == null) {
	    session = Session.buildSession(this.data.getChannel());
	}
	session.setDeviceId(this.data.getDeviceId());
	SessionManager.getInstance().put(sessionId, session);

	try {
	    MessageSender.getInstance().sendDownGeneralRspMsg(this);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }
}
