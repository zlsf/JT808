package JT809Data.netty.msghandler;

import JT809Data.model.OutboundMessage809;

public abstract class AbstractOutboundMessage implements OutboundMessage809 {

	private final int msgId;

	public AbstractOutboundMessage(int msgId) {
		this.msgId = msgId;
	}

	@Override
	public int getMsgId() {
		return msgId;
	}
}
