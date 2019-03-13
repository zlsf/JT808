
package model;

import java.util.HashMap;
import java.util.Map;

import message.InboundMessageHandler;

/**
 * 消息工厂
 * @author Administrator
 *
 */
public class MessageFactory {

	private final static Object locker = new Object();

	private MessageFactory() {
	}

	public static MessageFactory getInstance() {
		return MessageFactoryInstance.INSTANCE;
	}

	private static class MessageFactoryInstance {

		private static final MessageFactory INSTANCE = new MessageFactory();
	}

	/**
	 * 产品表
	 */
	private Map<Integer, InboundMessageHandler> handlerMap = new HashMap<Integer, InboundMessageHandler>() {

		{

		}
	};

	/**
	 * 生产消息
	 * @param messageId
	 * @return
	 */
	public InboundMessageHandler buildMessage(int messageId) {
		synchronized (this.locker) {
			if (handlerMap.containsKey(messageId))
				return handlerMap.get(messageId);
			return null;
		}
	}
}
