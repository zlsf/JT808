
package JT808Data.message;

import JT808Data.model.codec.OutboundMessage;

/**
 * 应答
 */
public interface PlatformAckMessage extends OutboundMessage {

	/**
	 * Sets the ack msg no.
	 *
	 * @param ackMsgNo the new ack msg no
	 */
	void setAckMsgNo(int ackMsgNo);

	/**
	 * Sets the ack msg id.
	 *
	 * @param ackMsgId the new ack msg id
	 */
	void setAckMsgId(int ackMsgId);
}
