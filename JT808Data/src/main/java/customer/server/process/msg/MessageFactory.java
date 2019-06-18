package customer.server.process.msg;

import java.util.HashMap;
import java.util.Map;

/**
 * ��Ϣ����
 * 
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
	}
    };

    /**
     * ����������Ϣ�����߼�
     * 
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