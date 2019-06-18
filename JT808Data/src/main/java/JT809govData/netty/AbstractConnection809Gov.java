package JT809govData.netty;

import JT809Data.model.JT809Constant;
import JT809govData.model.Connection809Gov;
import JT809govData.model.GnssPlatformIdGov809;
import JT809govData.model.JT809GovPacket;
import JT809govData.model.MsgHeader809Gov;
import JT809govData.model.OutboundMessage809Gov;
import JT809govData.model.Session809Gov;

public abstract class AbstractConnection809Gov implements Connection809Gov {


	private long msgNo;
	private GnssPlatformIdGov809 remoteId;
	private Session809Gov session;
	private volatile boolean authenticated;

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	@Override
	public void setSession(Session809Gov session) {
		this.session = session;
	}

	@Override
	public Session809Gov getSession() {
		return session;
	}

	@Override
	public void setRemoteId(GnssPlatformIdGov809 id) {
		this.remoteId = id;
	}

	@Override
	public GnssPlatformIdGov809 getRemoteId() {
		return remoteId;
	}

	@Override
	public void sendMessage(OutboundMessage809Gov message) {
		byte[] msgBodyBytes = message.getMsgBody();
		JT809GovPacket packet = new JT809GovPacket(newHeader(message.getMsgId(), msgBodyBytes.length), msgBodyBytes);
		byte[] frame = session.serializeMessage(packet);
		send(frame);
	}

	protected abstract void send(byte[] frame);

	private synchronized long getMsgNoAndIncrement() {
		if (msgNo >= 0xffffffffL) {
			msgNo = 0;
		}
		return msgNo++;
	}

	private MsgHeader809Gov newHeader(int msgId, int msgBodyLength) {
		MsgHeader809Gov header = new MsgHeader809Gov();
		header.setMsgLength(JT809Constant.FRAME_LENGTH_CONSTANT + msgBodyLength);
		header.setMsgNo(getMsgNoAndIncrement());
		header.setMsgId(msgId);
		header.setGnssPlatformId(session.getGnssPlatformId());
		header.setVersion(session.getVersion());
		header.setEncrypt(session.isEncrypt());
		header.setEncryptKey(session.getEncryptKey());
		return header;
	}
}
