package JT809govData.model;
public interface InboundMessageHandler809Gov {

	boolean canHandle(int msgId);
	
	void handle(Connection session, JT809GovPacket packet);
}
