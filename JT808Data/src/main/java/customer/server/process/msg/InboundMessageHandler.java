package customer.server.process.msg;

import customer.server.model.PacketData;
import customer.server.model.Session;

/**
 * ��ջ��Ϣ����
 */
public interface InboundMessageHandler {

    /**
     * Handle.
     *
     * @param session
     *            the session
     * @param packetData
     *            the packet data
     */
    public void handle(Session session, PacketData packetData);
}
