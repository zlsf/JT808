
package JT809govData.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT809govData.netty.MessageFactory809Gov;



/**
 * ��Ϣ������
 */
public class MessageProcessService {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(MessageProcessService.class);

	/**
	 * Instantiates a new message process service.
	 */
	public MessageProcessService() {
	}

	/**
	 * ������Ϣ.
	 *
	 * @param session the session
	 * @param packetData the packet data
	 */
	public void processPacketData(Connection809Gov session, JT809GovPacket packetData) {
		int msgId = packetData.getMsgId();
		InboundMessageHandler809Gov message = MessageFactory809Gov.getInstance().buildMessage(msgId);
		if (null == message)
			log.info("δʵ��0x{}", Integer.toHexString(msgId));
		try {
			message.handle(session, packetData);
		} catch (Exception ex) {
			log.info("ִ���쳣0x{}", Integer.toHexString(msgId));
		}
	}
}