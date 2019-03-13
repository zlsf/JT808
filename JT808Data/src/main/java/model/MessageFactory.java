
package model;

import java.util.HashMap;
import java.util.Map;

import Utils.JT808Constant;
import message.InboundMessageHandler;
import message.handler.TerminalHeartbeatReqHandler;

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
	private Map<Integer, Class<? extends InboundMessageHandler>> handlerMap = new HashMap<Integer, Class<? extends InboundMessageHandler>>() {

		{
			put(JT808Constant.MSG_ID_TERMINAL_HEARTBEAT_REQ, TerminalHeartbeatReqHandler.class);// 心跳
		}
	};

	/**
	 * 生产具体消息处理逻辑
	 * @param messageId
	 * @return
	 */
	public InboundMessageHandler buildMessage(int messageId) {
		synchronized (this.locker) {
			if (!handlerMap.containsKey(messageId))
				return null;

			Class<? extends InboundMessageHandler> clazz = handlerMap.get(messageId);
			try {
				InboundMessageHandler obj = clazz.newInstance();
				return obj;
			} catch (Exception e) {
				return null;
			}
		}
	}
}
