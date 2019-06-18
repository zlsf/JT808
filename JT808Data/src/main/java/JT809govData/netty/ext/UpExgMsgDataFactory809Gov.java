
package JT809govData.netty.ext;

import java.util.HashMap;
import java.util.Map;

import JT809Data.model.JT809Constant;
import JT809govData.model.InboundMessageHandler809Gov;
import JT809govData.netty.ext.UpExgMsgHandler809Gov;

/**
 * 消息工厂
 * @author Administrator
 *
 */
public class UpExgMsgDataFactory809Gov {

	private final static Object locker = new Object();

	private UpExgMsgDataFactory809Gov() {
	}

	public static UpExgMsgDataFactory809Gov getInstance() {
		return MessageFactory809GovInstance.INSTANCE;
	}

	private static class MessageFactory809GovInstance {

		private static final UpExgMsgDataFactory809Gov INSTANCE = new UpExgMsgDataFactory809Gov();
	}

	/**
	 * 产品表
	 */
	private Map<Integer, Class<? extends UpExgMsgDataHandler809Gov>> handlerMap = new HashMap<Integer, Class<? extends UpExgMsgDataHandler809Gov>>() {

		{
			put(JT809Constant.UP_EXG_MSG_REAL_LOCATION, UpExgMsgRealLocationHandler809Gov.class);
		}
	};

	/**
	 * 生产具体消息处理逻辑
	 * @param messageId
	 * @return
	 */
	public UpExgMsgDataHandler809Gov buildMessage(int messageId) {
		synchronized (this.locker) {
			if (!handlerMap.containsKey(messageId))
				return null;

			Class<? extends UpExgMsgDataHandler809Gov> clazz = handlerMap.get(messageId);
			try {
				UpExgMsgDataHandler809Gov obj = clazz.newInstance();
				return obj;
			} catch (Exception e) {
				return null;
			}
		}
	}
}
