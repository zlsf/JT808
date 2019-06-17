package JT809Data.model;

public interface InboundMessageHandler {

	boolean canHandle(int msgId);
	
	void handle(Connection session, JT809Packet packet);
}
