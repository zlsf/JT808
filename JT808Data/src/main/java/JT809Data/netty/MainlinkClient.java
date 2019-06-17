package JT809Data.netty;

import JT809Data.model.Session809;

public interface MainlinkClient {

	void connect(String host, int port);

	boolean isConnected();

	void login(long userId, String password, String downlinkHost, int downlinkPort);

	void setSession(Session809 session);

	void setPacketProcessor(JT809PacketProcessor processor);

	void setConnectionManager(JT809ConnectionManager connectionManager);
	
	void setIdleTimeout(int timeout);

	void close();
}
