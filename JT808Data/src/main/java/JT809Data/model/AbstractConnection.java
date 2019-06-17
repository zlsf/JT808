package JT809Data.model;

public abstract class AbstractConnection implements Connection {

	private long msgNo;
	private Session809 session;
	private volatile boolean authenticated;

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	@Override
	public void setSession(Session809 session) {
		this.session = session;
	}

	@Override
	public Session809 getSession() {
		return session;
	}

	@Override
	public void sendMessage(OutboundMessage809 message) {
		byte[] msgBodyBytes = message.getMsgBody();
		JT809Packet packet = new JT809Packet(newHeader(message.getMsgId(), msgBodyBytes.length), msgBodyBytes);
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

	private MsgHeader newHeader(int msgId, int msgBodyLength) {
		MsgHeader header = new MsgHeader();
		header.setMsgLength(JT809Constant.FRAME_LENGTH_CONSTANT + msgBodyLength);
		header.setMsgNo(getMsgNoAndIncrement());
		header.setMsgId(msgId);
		header.setGnssPlatformId(session.getGnssPlatformId());
		header.setVersionFlag(session.getVersionFlag());
		header.setEncrypt(session.isEncrypt());
		header.setEncryptKey(session.getEncryptKey());
		return header;
	}
}
