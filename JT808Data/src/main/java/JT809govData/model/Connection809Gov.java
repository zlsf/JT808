package JT809govData.model;
public interface Connection809Gov {

	void setSession(Session809 session);
	
	Session809 getSession();
	
	boolean isAuthenticated();

	void setAuthenticated(boolean authenticated);
	
	void sendMessage(OutboundMessage809Gov message);

	void close();
}
