
package message;

import model.Session;
import model.codec.JT808Packet;

/**
 * ��Ϣ����
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
