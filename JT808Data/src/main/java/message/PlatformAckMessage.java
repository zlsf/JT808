
package message;

import model.codec.OutboundMessage;

/**
 * Ó¦´ð
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
