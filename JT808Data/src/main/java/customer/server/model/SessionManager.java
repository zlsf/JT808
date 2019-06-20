package customer.server.model;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionManager {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(SessionManager.class);

    public static SessionManager getInstance() {
	return SessionManagerInstance.INSTANCE;
    }

    private static class SessionManagerInstance {

	private static final SessionManager INSTANCE = new SessionManager();
    }

    /** The session map. */
    private Map<String, Session> sessionMap;

    public Map<String, Session> getSessionMap() {
	return sessionMap;
    }

    /** The stop map. */
    private Map<DeviceId, String> deviceMap;

    public Map<DeviceId, String> getDeviceMap() {
	return deviceMap;
    }

    /**
     * Instantiates a new session manager.
     */
    public SessionManager() {
	sessionMap = new ConcurrentHashMap<>();
	deviceMap = new ConcurrentHashMap<>();
    }

    /**
     * 所有设备ID
     *
     * @return the all stop ids
     */
    public Set<DeviceId> getAllStopIds() {
	return this.deviceMap.keySet();
    }

    /**
     * 是否包含了SessionID
     *
     * @param sessionId
     *            the session id
     * @return true, if successful
     */
    public boolean containsKey(String sessionId) {
	return this.sessionMap.containsKey(sessionId);
    }

    /**
     * 是否包含了Session
     *
     * @param session
     *            the session
     * @return true, if successful
     */
    public boolean containsSession(Session session) {
	return this.sessionMap.containsValue(session);
    }

    /**
     * 根据sessionID获取 Session
     *
     * @param sessionId
     *            the session id
     * @return the session
     */
    public Session findBySessionId(String sessionId) {
	return this.sessionMap.get(sessionId);
    }

    /**
     * 获取站牌ID
     *
     * @param stopId
     *            the stop id
     * @return the session
     */
    public Session findByDeviceId(DeviceId deviceId) {
	String sessionId = this.deviceMap.get(deviceId);
	if (sessionId == null)
	    return null;

	return sessionMap.get(sessionId);
    }

    /**
     * 添加
     *
     * @param sessionId
     *            the session id
     * @param session
     *            the session
     * @return the session
     */
    public synchronized Session put(String sessionId, Session session) {
	if (session.getDeviceId() != null) {
	    deviceMap.put(session.getDeviceId(), session.getId());
	}
	return sessionMap.put(sessionId, session);
    }

    /**
     * 移除
     *
     * @param sessionId
     *            the session id
     * @return the session
     */
    public synchronized Session removeBySessionId(String sessionId) {
	if (sessionId == null) {
	    return null;
	}
	Session session = sessionMap.remove(sessionId);
	if (session != null && session.getDeviceId() != null) {
	    deviceMap.remove(session.getDeviceId());
	}
	return session;
    }
}
