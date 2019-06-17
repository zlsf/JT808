package JT809Data.model;

public interface Connection {

	void setSession(Session809 session);
	
	Session809 getSession();
	
	boolean isAuthenticated();

	void setAuthenticated(boolean authenticated);
	
	void sendMessage(OutboundMessage809 message);

	void close();
}
