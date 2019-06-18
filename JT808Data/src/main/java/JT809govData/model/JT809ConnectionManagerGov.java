package JT809govData.model;



import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class JT809ConnectionManagerGov {

	private Map<GnssPlatformIdGov809, Long> verifyCodeMap = new ConcurrentHashMap<>();

	private Set<Connection809Gov> mainlinkConnections = new CopyOnWriteArraySet<>();

	private Set<Connection809Gov> downlinkConnections = new CopyOnWriteArraySet<>();

	public void addVerifyCode(GnssPlatformIdGov809 id, Long verifyCode) {
		verifyCodeMap.put(id, verifyCode);
	}

	public Collection<Connection809Gov> mainlinkConnections() {
		return Collections.unmodifiableCollection(mainlinkConnections);
	}

	public Collection<Connection809Gov> downlinkConnections() {
		return Collections.unmodifiableCollection(downlinkConnections);
	}

	public Connection809Gov getMainlinkConnection(GnssPlatformIdGov809 id) {
		for (Connection809Gov conn : mainlinkConnections) {
			if (id.equals(conn.getRemoteId())) {
				return conn;
			}
		}
		return null;
	}

	public Connection809Gov getDownlinkConnection(GnssPlatformIdGov809 id) {
		for (Connection809Gov conn : downlinkConnections) {
			if (id.equals(conn.getRemoteId())) {
				return conn;
			}
		}
		return null;
	}

	public void addMainlinkConnection(Connection809Gov conn) {
		mainlinkConnections.add(conn);
	}

	public void removeMainlinkConnection(Connection809Gov conn) {
		mainlinkConnections.remove(conn);
	}

	public void addDownlinkConnection(Connection809Gov conn) {
		downlinkConnections.add(conn);
	}

	public void removeDownlinkConnection(Connection809Gov conn) {
		downlinkConnections.remove(conn);
	}
}
