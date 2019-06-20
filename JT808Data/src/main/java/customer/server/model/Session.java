package customer.server.model;

import java.net.SocketAddress;

import io.netty.channel.Channel;

/**
 * Session
 *
 * @author
 * 
 *         <p>
 *         Modification History:
 *         </p>
 *         <p>
 *         Date Author Description
 *         </p>
 *         <p>
 *         ------------------------------------------------------------------
 *         </p>
 *         <p>
 *         </p>
 *         <p>
 *         </p>
 */
public class Session {

    /** The id. */
    private String id;

    /** The channel. */
    private Channel channel;

    /** The logged in. */
    private boolean loggedIn;
    
    private String deviceId;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
	return id;
    }

    /**
     * Gets the channel.
     *
     * @return the channel
     */
    public Channel getChannel() {
	return channel;
    }

    /**
     * Checks if is logged in.
     *
     * @return true, if is logged in
     */
    public boolean isLoggedIn() {
	return loggedIn;
    }

    /**
     * Sets the logged in.
     *
     * @param loggedIn
     *            the new logged in
     */
    public void setLoggedIn(boolean loggedIn) {
	this.loggedIn = loggedIn;
    }

    /**
     * 客戶端地址
     *
     * @return the remote
     */
    public SocketAddress getRemote() {
	return channel.remoteAddress();
    }

    /**
     * 客户端ID
     *
     * @param channel
     *            the channel
     * @return the string
     */
    public static String buildId(Channel channel) {
	return channel.toString();
    }

    /**
     * 创建Session
     *
     * @param channel
     *            the channel
     * @return the session
     */
    public static Session buildSession(Channel channel) {
	Session session = new Session();
	session.id = buildId(channel);
	session.channel = channel;
	return session;
    }

    public void setId(String id) {
	this.id = id;
    }

    public void setChannel(Channel channel) {
	this.channel = channel;
    }
    

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Session other = (Session) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }
}