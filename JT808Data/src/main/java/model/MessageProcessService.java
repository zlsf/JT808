
package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import message.InboundMessageHandler;
import model.codec.JT808Packet;

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
	public void processPacketData(Session session, JT808Packet packetData) {
		int msgId = packetData.getMsgId();
		InboundMessageHandler message = MessageFactory.getInstance().buildMessage(msgId);
		if (null == message)
			log.info("未实现0x{}", Integer.toHexString(msgId));
		try {
			message.handle(session, packetData);
		} catch (Exception ex) {
			log.info("执行异常0x{}", Integer.toHexString(msgId));
		}
	}
}
