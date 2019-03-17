
package message.handler;

import java.util.Date;

import Utils.BcdCodeUtil;
import Utils.ByteArrayUtil;
import message.InboundMessageHandler;
import model.Session;
import model.codec.DriverInfo;
import model.codec.JT808Packet;

public class DriverInfoReportReqHandler implements InboundMessageHandler {

	@Override
	public void handle(Session session, JT808Packet packetData) {
		byte[] body = packetData.getMsgBodyBytes();
		int status = ByteArrayUtil.getUnsignedByte(body, 0);
		Date time = BcdCodeUtil.bcdToDateTime(ByteArrayUtil.copyOfRange(body, 1, 7));
		if (status == 0x01) {
			int icResult = ByteArrayUtil.getUnsignedByte(body, 7);
			if (icResult == 0x00) {
				DriverInfo driver = DriverInfo.restore(ByteArrayUtil.copyOfRange(body, 8, body.length));
			}
		}
	}

}
