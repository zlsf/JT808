
package JT809Data.netty;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT809Data.model.Connection;
import JT809Data.model.InboundMessageHandler;
import JT809Data.model.JT809Packet;

public class JT809PacketProcessor {

	private static final Logger log = LoggerFactory.getLogger(JT809PacketProcessor.class);

	private Set<InboundMessageHandler> handlers;

	public void processPacket(Connection conn, JT809Packet packet) {
		boolean handled = false;
		for (InboundMessageHandler handler : handlers) {
			if (handler.canHandle(packet.getMsgId())) {
				handled = true;
				handler.handle(conn, packet);
			}
		}
		if (!handled) {
			log.info("handler for 0x{} not implement", Integer.toHexString(packet.getMsgId()));
		}
	}

}
