
package message;

import model.codec.OutboundMessage;
import model.codec.TerminalId;

public abstract class AbstractOutboundMessage implements OutboundMessage {

	protected final int msgId;
	protected final TerminalId terminalId;

	public AbstractOutboundMessage(int msgId, TerminalId terminalId) {
		this.msgId = msgId;
		this.terminalId = terminalId;
	}

	@Override
	public int getMsgId() {
		return msgId;
	}

	@Override
	public TerminalId getTerminalId() {
		return terminalId;
	}
}
