
package customer.server.process.msg;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.kj.datacenter.core.Constant;

import customer.server.model.PackData;
import customer.server.process.msg.detail.HeartBitMessage;

/**
 * 锟斤拷息锟斤拷锟斤拷
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
	 * 锟斤拷品锟斤拷
	 */
	private Map<Integer, Class<? extends AbstractMessage>> dic = new HashMap<Integer, Class<? extends AbstractMessage>>() {

		{
			put(Constant.HRAET_BIT, HeartBitMessage.class);
			put(Constant.LOGIN, HeartBitMessage.class);
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
			Constructor cs = dic.get(msgFlag).getConstructor(PackData.class);
			cs.setAccessible(true);
			T result = (T) cs.newInstance(args);
			return result;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
