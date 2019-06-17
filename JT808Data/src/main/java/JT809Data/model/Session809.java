
package JT809Data.model;

public class Session809 {

	private JT809PacketDeser packetDeser;
	private GnssPlatformId gnssPlatformId;
	private byte[] versionFlag;
	private boolean encrypt;
	private long encryptKey;

	public Session809(GnssPlatformId gnssPlatformId, byte[] versionFlag) {
		this(gnssPlatformId, versionFlag, false, 0, 0, 0, 0);
	}

	public Session809(GnssPlatformId gnssPlatformId, byte[] versionFlag, long encryptKey, long m1, long ia1, long ic1) {
		this(gnssPlatformId, versionFlag, true, encryptKey, m1, ia1, ic1);
	}

	public Session809(GnssPlatformId gnssPlatformId, byte[] versionFlag, boolean encrypt, long encryptKey, long m1,
			long ia1, long ic1) {
		this(gnssPlatformId, versionFlag, encrypt, encryptKey, new JT809PacketDeser(m1, ia1, ic1));
	}

	public Session809(GnssPlatformId gnssPlatformId, byte[] versionFlag, boolean encrypt, long encryptKey,
			JT809PacketDeser packetDeser) {
		this.gnssPlatformId = gnssPlatformId;
		this.versionFlag = versionFlag;
		this.encrypt = encrypt;
		this.encryptKey = encryptKey;
		this.packetDeser = packetDeser;
	}

	public GnssPlatformId getGnssPlatformId() {
		return gnssPlatformId;
	}

	public byte[] getVersionFlag() {
		return versionFlag;
	}

	public boolean isEncrypt() {
		return encrypt;
	}

	public long getEncryptKey() {
		return encryptKey;
	}

	public JT809PacketDeser getPacketDeser() {
		return packetDeser;
	}

	public byte[] serializeMessage(JT809Packet packet) {
		return packetDeser.serialize(packet);
	}

	public static long randomCode() {
		return (long) (Math.random() * Integer.MAX_VALUE);
	}

}
