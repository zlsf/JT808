
package message.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Utils.ByteArrayUtil;
import Utils.JT808Constant;
import message.InboundMessageHandler;
import model.Session;
import model.codec.GnssAttachment;
import model.codec.GnssPosition;
import model.codec.JT808Packet;

/**
 * 位置信息定期汇报
 * @author Administrator
 *
 */
public class TerminalLocationReportHandler implements InboundMessageHandler {

	
	private static Logger log = LoggerFactory.getLogger(TerminalLocationReportHandler.class);

	@Override
	public void handle(Session session, JT808Packet packetData) {
		byte[] msgBody = packetData.getMsgBodyBytes();
		GnssPosition position = GnssPosition.restore(packetData.getTerminalId().toString(),
				ByteArrayUtil.copyOfRange(msgBody, 0, 28));
		if (msgBody.length > 28) {
			List<GnssAttachment> attachments = GnssAttachment
					.restoreAttachments(ByteArrayUtil.copyOfRange(msgBody, 28, msgBody.length));
		}
		//TODO： 处理 GPS位置信息
		log.info("获取到位置信息.......");
		log.info(position.toString());

		// 收到后的通用应答
		session.sendPlatformGeneralRsp(packetData.getTerminalId(), packetData.getMsgNo(), packetData.getMsgId(),
				JT808Constant.GENERAL_RSP_RESULT_SUCCESS);
	}

}
