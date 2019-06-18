
package JT809govData.model;

public interface Connection809Gov {

	void setSession(Session809Gov session);

	Session809Gov getSession();

	void setRemoteId(GnssPlatformIdGov809 id);

	GnssPlatformIdGov809 getRemoteId();

	String getRemoteAddress();

	boolean isAuthenticated();

	void setAuthenticated(boolean authenticated);

	void sendMessage(OutboundMessage809Gov message);

	void close();
}
