
package JT808Data.model.codec;

/**
 * 消息ID封装
 */
public class MessageId {

	/** id号 */
	private final int msgId;

	/** 终端手机号 */
	private final TerminalId terminalId;

	public MessageId(int msgId, TerminalId terminalId) {
		this.msgId = msgId;
		this.terminalId = terminalId;
	}

	/**
	 * Gets the msg id.
	 *
	 * @return the msg id
	 */
	public int getMsgId() {
		return msgId;
	}

	/**
	 * Gets the terminal id.
	 *
	 * @return the terminal id
	 */
	public TerminalId getTerminalId() {
		return terminalId;
	}

	/* hashCode
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + msgId;
		result = prime * result + ((terminalId == null) ? 0 : terminalId.hashCode());
		return result;
	}

	/* 判等
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageId other = (MessageId) obj;
		if (msgId != other.msgId)
			return false;
		if (terminalId == null) {
			if (other.terminalId != null)
				return false;
		} else if (!terminalId.equals(other.terminalId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MessageId [msgId=" + msgId + ", terminalId=" + terminalId + "]";
	}

}
