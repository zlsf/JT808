
package JT809govData.model;

public class Session809Gov {

	private JT809PacketDeserGov packetDeser;
	private GnssPlatformIdGov809 gnssPlatformId;
	private byte[] version;
	private boolean encrypt;
	private long encryptKey;

	public Session809Gov(GnssPlatformIdGov809 gnssPlatformId, byte[] versionFlag) {
		this(gnssPlatformId, versionFlag, false, 0, 0, 0, 0);
	}

	public Session809Gov(GnssPlatformIdGov809 gnssPlatformId, byte[] versionFlag, long encryptKey, long m1, long ia1,
			long ic1) {
		this(gnssPlatformId, versionFlag, true, encryptKey, m1, ia1, ic1);
	}

	public Session809Gov(GnssPlatformIdGov809 gnssPlatformId, byte[] versionFlag, boolean encrypt, long encryptKey,
			long m1, long ia1, long ic1) {
		this(gnssPlatformId, versionFlag, encrypt, encryptKey, new JT809PacketDeserGov(m1, ia1, ic1));
	}

	public Session809Gov(GnssPlatformIdGov809 gnssPlatformId, byte[] versionFlag, boolean encrypt, long encryptKey,
			JT809PacketDeserGov packetDeser) {
		this.gnssPlatformId = gnssPlatformId;
		this.version = versionFlag;
		this.encrypt = encrypt;
		this.encryptKey = encryptKey;
		this.packetDeser = packetDeser;
	}

	public GnssPlatformIdGov809 getGnssPlatformId() {
		return gnssPlatformId;
	}

	public byte[] getVersion() {
		return version;
	}

	public boolean isEncrypt() {
		return encrypt;
	}

	public long getEncryptKey() {
		return encryptKey;
	}

	public JT809PacketDeserGov getPacketDeser() {
		return packetDeser;
	}

	public byte[] serializeMessage(JT809GovPacket packet) {
		return packetDeser.serialize(packet);
	}

}
