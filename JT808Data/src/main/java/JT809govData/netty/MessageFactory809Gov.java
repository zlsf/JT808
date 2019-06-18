
package JT809govData.netty;

import java.util.HashMap;
import java.util.Map;

import JT809govData.model.InboundMessageHandler809Gov;

/**
 * ��Ϣ����
 * @author Administrator
 *
 */
public class MessageFactory809Gov {

	private final static Object locker = new Object();

	private MessageFactory809Gov() {
	}

	public static MessageFactory809Gov getInstance() {
		return MessageFactory809GovInstance.INSTANCE;
	}

	private static class MessageFactory809GovInstance {

		private static final MessageFactory809Gov INSTANCE = new MessageFactory809Gov();
	}

	/**
	 * ��Ʒ��
	 */
	private Map<Integer, Class<? extends InboundMessageHandler809Gov>> handlerMap = new HashMap<Integer, Class<? extends InboundMessageHandler809Gov>>() {

		{
			
		}
	};

	/**
	 * ����������Ϣ�����߼�
	 * @param messageId
	 * @return
	 */
	public InboundMessageHandler809Gov buildMessage(int messageId) {
		synchronized (this.locker) {
			if (!handlerMap.containsKey(messageId))
				return null;

			Class<? extends InboundMessageHandler809Gov> clazz = handlerMap.get(messageId);
			try {
				InboundMessageHandler809Gov obj = clazz.newInstance();
				return obj;
			} catch (Exception e) {
				return null;
			}
		}
	}
}
