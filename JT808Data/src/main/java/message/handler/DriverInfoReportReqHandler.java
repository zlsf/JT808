
package message.handler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Utils.BcdCodeUtil;
import Utils.ByteArrayUtil;
import message.InboundMessageHandler;
import model.Session;
import model.codec.DriverInfo;
import model.codec.JT808Packet;

/**
 * 解析驾驶员信息
 * @author Administrator
 *
 */
public class DriverInfoReportReqHandler implements InboundMessageHandler {

	private static Logger log = LoggerFactory.getLogger(DriverInfoReportReqHandler.class);

	@Override
	public void handle(Session session, JT808Packet packetData) {

		log.info("收到驾驶员信息......");
		byte[] body = packetData.getMsgBodyBytes();
		int status = ByteArrayUtil.getUnsignedByte(body, 0);
		Date time = BcdCodeUtil.bcdToDateTime(ByteArrayUtil.copyOfRange(body, 1, 7));
		if (status == 0x01) {
			int icResult = ByteArrayUtil.getUnsignedByte(body, 7);
			if (icResult == 0x00) {
				DriverInfo driver = DriverInfo.restore(ByteArrayUtil.copyOfRange(body, 8, body.length));
				log.info("驾驶员信息:" + driver.toString());
			}
		}
	}

}
