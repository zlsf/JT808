package JT809Data.netty;

import JT809Data.model.Connection;

public class JT809ConnectionManager {

	private long verifyCode;

	private Connection mainlinkConnection;

	private Connection downlinkConnection;

	public void setVerifyCode(long verifyCode) {
		this.verifyCode = verifyCode;
	}

	public long getVerifyCode() {
		return verifyCode;
	}

	public void setMainlinkConnection(Connection mainlinkConnection) {
		this.mainlinkConnection = mainlinkConnection;
	}

	public void setDownlinkConnection(Connection downlinkConnection) {
		this.downlinkConnection = downlinkConnection;
	}

	public Connection getMainlinkConnection() {
		return mainlinkConnection;
	}

	public Connection getDownlinkConnection() {
		return downlinkConnection;
	}
}
