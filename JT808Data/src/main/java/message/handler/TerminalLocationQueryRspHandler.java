
package message.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Utils.ByteArrayUtil;
import message.InboundMessageHandler;
import model.Session;
import model.codec.GnssAttachment;
import model.codec.GnssPosition;
import model.codec.JT808Packet;

public class TerminalLocationQueryRspHandler implements InboundMessageHandler {

	private static Logger log = LoggerFactory.getLogger(TerminalLocationQueryRspHandler.class);

	@Override
	public void handle(Session session, JT808Packet packetData) {
		byte[] msgBody = packetData.getMsgBodyBytes();
		int ackMsgNo = ByteArrayUtil.getUnsignedShort(msgBody, 0);
		GnssPosition position = GnssPosition.restore(packetData.getTerminalId().toString(),
				ByteArrayUtil.copyOfRange(msgBody, 2, 30));
		if (msgBody.length > 30) {
			List<GnssAttachment> attachments = GnssAttachment
					.restoreAttachments(ByteArrayUtil.copyOfRange(msgBody, 30, msgBody.length));
		}
		// TODO :位置信息处理
		log.info("获取到位置信息.......");
		log.info(position.toString());

	}
}
