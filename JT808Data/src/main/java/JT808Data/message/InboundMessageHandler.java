
package JT808Data.message;

import JT808Data.model.Session;
import JT808Data.model.codec.JT808Packet;

/**
 * ��ջ��Ϣ����
 */
public interface InboundMessageHandler {

	/**
	 * Handle.
	 *
	 * @param session the session
	 * @param packetData the packet data
	 */
	public void handle(Session session, JT808Packet packetData);
}
