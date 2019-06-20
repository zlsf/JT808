package customer.server.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kj.datacenter.core.ProtocolConstant;

import customer.server.model.Session;
import customer.server.model.SessionManager;
import customer.server.process.msg.AbstractMessage;
import customer.server.process.msg.DownGeneralRsp;
import customer.server.process.msg.MessageEncoder;
import customer.server.process.msg.ReceivedMessage;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

public class MessageSender {

    private static final Logger log = LoggerFactory.getLogger(MessageSender.class);

    private MessageSender() {
    }

    public static MessageSender getInstance() {
	return MessageSenderInstance.INSTANCE;
    }

    private static class MessageSenderInstance {

	private static final MessageSender INSTANCE = new MessageSender();
    }

    /**
     * ���͵��豸
     * 
     * @param channel
     * @param data
     * @throws InterruptedException
     */
    public static void sendToDevice(Channel channel, byte[] data) throws InterruptedException {
	for (int i = 0; i < 3; i++) {
	    ChannelFuture future = channel.writeAndFlush(Unpooled.copiedBuffer(data)).sync();
	    if (future.isSuccess())
		break;
	}
    }

    /**
     * ��ȡ��Ϣ��
     * 
     * @param channel
     * @return
     */
    private static int getMsgNo(Channel channel) {
	Session session = SessionManager.getInstance().findBySessionId(Session.buildId(channel));
	if (session == null) {
	    return 0;
	}
	return session.getMsgNoAndIncrement();
    }

    /**
     * ����:��¼
     * 
     * @param data
     * @throws InterruptedException
     */
    private void processUpLoginReqMsg(AbstractMessage data) throws InterruptedException {
	log.debug("�豸��¼��{}", data);
	System.out.println("�豸��½.....");
	String sessionId = Session.buildId(data.getChannel());
	Session session = SessionManager.getInstance().findBySessionId(sessionId);
	if (session == null) {
	    session = Session.buildSession(data.getChannel());
	}
	session.setDeviceId(data.getDeviceId());
	session.setLoggedIn(true);
	SessionManager.getInstance().put(sessionId, session);
	sendDownGeneralRspMsg(data);
    }

    /**
     * ���У�һ����ϢӦ��
     * 
     * @param data
     * @throws InterruptedException
     */
    public static void sendDownGeneralRspMsg(ReceivedMessage data) throws InterruptedException {
	DownGeneralRsp rsp = new DownGeneralRsp(data.getDeviceId());
	rsp.setAckMsgId(data.getMsgId());
	rsp.setAckMsgNo(data.getMsgNo());
	rsp.setResult(ProtocolConstant.GENERAL_RSP_RESULT_SUCCESS);
	int msgNo = getMsgNo(data.getChannel());
	byte[] msg = (new MessageEncoder()).encodeMessage(rsp, msgNo);
	sendToDevice(data.getChannel(), msg);
    }

}
