
package Utils;

/**
 * 常量.
 */
public class Constant {

	/**  设备对中心. */
	public final static int Service_Model_D2C = 0;

	/**  中心对中心. */
	public final static int Service_Model_C2C = 1;

	/** D2C 的心跳 D-->C */
	public final static int HeartBeat_D2C = 0x0002;
	/** D2C 的心跳应答 C-->D */
	public final static int HeartBeat_Answer = 0x8001;
	/**结束符号*/
	public final static byte FRAME_DELIMITER = 0x7E;
	/**GPS*/
	public final static int GPS_ARRIVE = (byte) 0x0200;

}
