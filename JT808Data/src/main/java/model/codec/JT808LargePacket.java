package model.codec;


import Utils.ByteArrayUtil;

/**
 * 808�Ĵ��
 */
public class JT808LargePacket {

	/** ��ϢID */
	private final int msgId;
	
	/** �ն��ֻ��� */
	private final TerminalId terminalId;
	
	/** ��ʼ����Ϣ�� */
	private final int startMsgNo;
	
	/**  */
	private int received;
	
	/** ��Ϣ������ */
	private byte[][] msgBodyArray;


	public JT808LargePacket(JT808Packet firstPacket) {
		this.msgId = firstPacket.getMsgId();
		this.terminalId = firstPacket.getTerminalId();
		this.startMsgNo = firstPacket.getMsgNo();
		this.msgBodyArray = new byte[firstPacket.getSubpacketCount()][];
		this.received = 1;
		msgBodyArray[firstPacket.getSubpacketNo() - 1] = firstPacket.getMsgBodyBytes();
	}

	/**
	 * Gets the msg no.
	 *
	 * @return the msg no
	 */
	public int getMsgNo() {
		return startMsgNo;
	}

	/**
	 * Gets the subpacket count.
	 *
	 * @return the subpacket count
	 */
	public int getSubpacketCount() {
		return msgBodyArray.length;
	}

	/**
	 * Adds the.
	 *
	 * @param packet the packet
	 */
	public void add(JT808Packet packet) {
		if (msgBodyArray[packet.getSubpacketNo() - 1] == null) {
			received += 1;
			msgBodyArray[packet.getSubpacketNo() - 1] = packet.getMsgBodyBytes();
		}
	}

	/**
	 * ����Ƿ�����
	 *
	 * @return true, if is fulfill
	 */
	public boolean isFulfill() {
		if (received == msgBodyArray.length) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ת��Ϊ808֡��
	 *
	 * @return the JT 808 packet
	 */
	public JT808Packet toJT808Packet() {
		JT808Packet pd = new JT808Packet(msgId, terminalId);
		pd.setMsgNo(startMsgNo);
		pd.setMsgBodyBytes(ByteArrayUtil.concact(msgBodyArray));
		return pd;
	}
}
