
package model;

import java.util.HashMap;
import java.util.Map;

import Utils.JT808Constant;
import message.InboundMessageHandler;
import message.handler.TerminalAuthenticationReqHandler;
import message.handler.TerminalHeartbeatReqHandler;
import message.handler.TerminalLocationQueryRspHandler;
import message.handler.TerminalLocationReportHandler;
import message.handler.TerminalRegisterReqHandler;
import message.handler.TerminalUnregisterReqHandler;

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
			put(JT808Constant.MSG_ID_TERMINAL_REGISTER_REQ, TerminalRegisterReqHandler.class);// 注册
			put(JT808Constant.MSG_ID_TERMINAL_UNREGISTER_REQ, TerminalUnregisterReqHandler.class);// 注销
			put(JT808Constant.MSG_ID_TERMINAL_AUTH_REQ, TerminalAuthenticationReqHandler.class);// 鉴权
			put(JT808Constant.MSG_ID_TERMINAL_LOCATION_QUERY_RSP, TerminalLocationQueryRspHandler.class);// 位置设备应答查询
			put(JT808Constant.MSG_ID_TERMINAL_LOCATION_REPORT, TerminalLocationReportHandler.class);// 位置定期汇报
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
