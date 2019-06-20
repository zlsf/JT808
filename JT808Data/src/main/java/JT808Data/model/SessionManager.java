package JT808Data.model;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT808Data.model.codec.TerminalId;

/**
 * session 管理器.
 *
 * @author <p>Modification History:</p>
 * <p>Date       Author      Description</p>
 * <p>------------------------------------------------------------------</p>
 * <p>  </p>
 * <p>  </p>
 */
public class SessionManager {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(SessionManager.class);

	private SessionManager() {
		sessionMap = new ConcurrentHashMap<>();
		terminalMap = new ConcurrentHashMap<>();
	}

	public static SessionManager getInstance() {
		return SessionManagerInstance.INSTANCE;
	}

	private static class SessionManagerInstance {

		private static final SessionManager INSTANCE = new SessionManager();
	}

	// Session 字典
	private Map<String, Session> sessionMap;

	/**
	 * 终端手机
	 */
	private Map<TerminalId, String> terminalMap;

	/**
	 * Gets the all terminal ids.
	 *
	 * @return the all terminal ids
	 */
	public Set<TerminalId> getAllTerminalIds() {
		return terminalMap.keySet();
	}

	/**
	 * Gets the session map.
	 *
	 * @return the session map
	 */
	public Map<String, Session> getSessionMap() {
		return sessionMap;
	}

	/**
	 * Gets the terminal map.
	 *
	 * @return the terminal map
	 */
	public Map<TerminalId, String> getTerminalMap() {
		return terminalMap;
	}

	/**
	 * Contains key.
	 *
	 * @param sessionId the session id
	 * @return true, if successful
	 */
	public boolean containsKey(String sessionId) {
		return sessionMap.containsKey(sessionId);
	}

	/**
	 * Contains session.
	 *
	 * @param session the session
	 * @return true, if successful
	 */
	public boolean containsSession(Session session) {
		return sessionMap.containsValue(session);
	}

	/**
	 * Gets the session.
	 *
	 * @param sessionId the session id
	 * @return the session
	 */
	public Session getSession(String sessionId) {
		return sessionMap.get(sessionId);
	}

	/**
	 * 根据手机号获取session.
	 *
	 * @param terminalId the terminal id
	 * @return the session by terminal id
	 */
	public Session getSessionByTerminalId(TerminalId terminalId) {
		String sessionId = terminalMap.get(terminalId);
		if (sessionId != null) {
			return sessionMap.get(sessionId);
		}
		return null;
	}

	/**
	 * 添加session.
	 *
	 * @param sessionId the session id
	 * @param session the session
	 * @return the session
	 */
	public Session addSession(String sessionId, Session session) {
		log.info("add session: {}", sessionId);
		return sessionMap.put(sessionId, session);
	}

	/**
	 * 移除session
	 *
	 * @param sessionId the session id
	 * @return the session
	 */
	public Session removeSession(String sessionId) {
		Session session = sessionMap.remove(sessionId);
		if (session != null) {
			log.info("remove session: {}", sessionId);
			session.close();
		}
		return session;
	}

	/**
	 * 天机手机号
	 *
	 * @param terminalId the terminal id
	 * @param sessionId the session id
	 */
	public void addTerminal(TerminalId terminalId, String sessionId) {
		String oldSessionId = terminalMap.get(terminalId);
		if (oldSessionId != null && !oldSessionId.equals(sessionId)) {
			removeTerminal(terminalId, oldSessionId);
		}
		terminalMap.put(terminalId, sessionId);
	}

	/**
	 * 移除手机号
	 *
	 * @param terminalId the terminal id
	 * @param sessionId the session id
	 */
	public void removeTerminal(TerminalId terminalId, String sessionId) {
		String oldSessionId = terminalMap.get(terminalId);
		if (oldSessionId != null && oldSessionId.equals(sessionId)) {
			terminalMap.remove(sessionId);
			this.removeSession(sessionId);
		}
	}
}
