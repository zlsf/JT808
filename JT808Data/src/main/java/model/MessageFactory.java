
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
 * ��Ϣ����
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
	 * ��Ʒ��
	 */
	private Map<Integer, Class<? extends InboundMessageHandler>> handlerMap = new HashMap<Integer, Class<? extends InboundMessageHandler>>() {

		{
			put(JT808Constant.MSG_ID_TERMINAL_HEARTBEAT_REQ, TerminalHeartbeatReqHandler.class);// ����
			put(JT808Constant.MSG_ID_TERMINAL_REGISTER_REQ, TerminalRegisterReqHandler.class);// ע��
			put(JT808Constant.MSG_ID_TERMINAL_UNREGISTER_REQ, TerminalUnregisterReqHandler.class);// ע��
			put(JT808Constant.MSG_ID_TERMINAL_AUTH_REQ, TerminalAuthenticationReqHandler.class);// ��Ȩ
			put(JT808Constant.MSG_ID_TERMINAL_LOCATION_QUERY_RSP, TerminalLocationQueryRspHandler.class);// λ���豸Ӧ���ѯ
			put(JT808Constant.MSG_ID_TERMINAL_LOCATION_REPORT, TerminalLocationReportHandler.class);// λ�ö��ڻ㱨
		}
	};

	/**
	 * ����������Ϣ�����߼�
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
