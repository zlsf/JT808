
package JT808Data.message.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT808Data.message.InboundMessageHandler;
import JT808Data.model.Session;
import JT808Data.model.codec.GnssAttachment;
import JT808Data.model.codec.GnssPosition;
import JT808Data.model.codec.JT808Packet;
import Utils.ByteArrayUtil;

/**
 * 位置信息查询设备应答
 * @author Administrator
 *
 */
public class TerminalLocationQueryRspHandler implements InboundMessageHandler {

	private static Logger log = LoggerFactory.getLogger(TerminalLocationQueryRspHandler.class);

	@Override
	public void handle(Session session, JT808Packet packetData) {
		byte[] msgBody = packetData.getMsgBodyBytes();
		int ackMsgNo = ByteArrayUtil.getUnsignedShort(msgBody, 0);
		// 构建gps信息
		GnssPosition position = GnssPosition.restore(packetData.getTerminalId().toString(),
				ByteArrayUtil.copyOfRange(msgBody, 2, 30));
		// 附加信息
		if (msgBody.length > 30) {
			List<GnssAttachment> attachments = GnssAttachment
					.restoreAttachments(ByteArrayUtil.copyOfRange(msgBody, 30, msgBody.length));
		}
		// TODO :位置信息处理
		log.info("获取到位置信息.......");
		log.info(position.toString());

	}
}
