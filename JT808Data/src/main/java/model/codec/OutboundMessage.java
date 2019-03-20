
package model.codec;


/**
 * 出站信息接口
 */
public interface OutboundMessage {

	/**
	 * Gets the msg id.
	 *
	 * @return the msg id
	 */
	int getMsgId();

	/**
	 * Gets the terminal id.
	 *
	 * @return the terminal id
	 */
	TerminalId getTerminalId();

	/**
	 * Gets the msg body.
	 *
	 * @return the msg body
	 */
	byte[] getMsgBody();
}
