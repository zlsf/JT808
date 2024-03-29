
package JT808Data.message.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import JT808Data.message.InboundMessageHandler;
import JT808Data.model.Session;
import JT808Data.model.codec.JT808Packet;
import JT808Data.model.codec.TerminalRegister;
import JT808Data.model.codec.TerminalRegisterResult;
import Utils.ByteArrayUtil;

/**
 * 终端注册处理 其中包含了车辆信息
 * 
 * @author zlsf
 *
 */
public class TerminalRegisterReqHandler implements InboundMessageHandler {

	private static final Logger log = LoggerFactory.getLogger(TerminalRegisterReqHandler.class);

	public void handle(Session session, JT808Packet packetData) {
		log.info("收到注册信息");
		byte[] body = packetData.getMsgBodyBytes();
		TerminalRegister tr = new TerminalRegister(packetData.getTerminalId().toString());
		tr.setProvinceCode(ByteArrayUtil.getUnsignedShort(body, 0));
		tr.setCityCode(ByteArrayUtil.getUnsignedShort(body, 2));
		tr.setProducerId(ByteArrayUtil.toString(ByteArrayUtil.copyOfRange(body, 4, 9)));
		tr.setTerminalModel(ByteArrayUtil.toString(ByteArrayUtil.copyOfRange(body, 9, 29)));
		tr.setTerminalId(ByteArrayUtil.toString(ByteArrayUtil.copyOfRange(body, 29, 36)));
		tr.setVehicleColor(ByteArrayUtil.getUnsignedByte(body, 36));
		tr.setVehicleNo(ByteArrayUtil.toString(ByteArrayUtil.copyOfRange(body, 37, body.length)));
		// TODO :注册
		log.info("注册信息"+tr.toString());
		TerminalRegisterResult result = new TerminalRegisterResult(TerminalRegisterRsp.RESULT_SUCCESS, "qqqqq");// 这里认证

		TerminalRegisterRsp rsp = new TerminalRegisterRsp(packetData.getTerminalId());
		rsp.setAckMsgNo(packetData.getMsgNo());
		rsp.setResult(result);
		session.sendAckMessage(rsp);
	}

}
