
package JT809govData.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT809govData.model.InboundMessageHandler809Gov;
import JT809govData.model.JT809GovPacket;



/**
 * 消息处理器
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
	 * 处理消息.
	 *
	 * @param session the session
	 * @param packetData the packet data
	 */
	public void processPacketData(Session session, JT809GovPacket packetData) {
		int msgId = packetData.getMsgId();
		InboundMessageHandler809Gov message = MessageFactory809Gov.getInstance().buildMessage(msgId);
		if (null == message)
			log.info("未实现0x{}", Integer.toHexString(msgId));
		try {
			message.handle(session, packetData);
		} catch (Exception ex) {
			log.info("执行异常0x{}", Integer.toHexString(msgId));
		}
	}
}
