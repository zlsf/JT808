package customer.server.process.msg;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.kj.datacenter.core.ProtocolConstant;

import customer.server.model.PacketData;
import customer.server.process.msg.detail.HeartBitMessage;

/**
 * 消息工厂
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
     * 产品表
     */
    private Map<Integer, Class<? extends AbstractMessage>> dic = new HashMap<Integer, Class<? extends AbstractMessage>>() {
	{
	    put(ProtocolConstant.MSG_ID_UP_HEAR_BIT, HeartBitMessage.class);
	}
    };

    /**
     * Gets the cmd.
     *
     * @param <T>
     *            the generic type
     * @param msgFlag
     *            the msg flag
     * @param args
     *            the args
     * @return the cmd
     */
    public <T extends AbstractMessage> T getCmd(Integer msgFlag, Object... args) {
	if (!dic.containsKey(msgFlag))
	    return null;

	try {
	    Constructor cs = dic.get(msgFlag).getConstructor(PacketData.class);
	    cs.setAccessible(true);
	    T result = (T) cs.newInstance(args);
	    return result;
	} catch (Exception e) {
	    throw new RuntimeException();
	}
    }
}