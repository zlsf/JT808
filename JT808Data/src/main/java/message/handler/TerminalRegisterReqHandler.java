package com.haiyisoft.jt808.netty.handler.codec.jt808;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.haiyisoft.jt808.application.TerminalManagerService;
import com.haiyisoft.jt808.domain.model.TerminalRegister;
import com.haiyisoft.jt808.domain.model.TerminalRegisterResult;
import com.haiyisoft.jt808.netty.handler.Session;
import com.haiyisoft.jt808.util.ByteArrayUtil;
/**
 * ÖÕ¶Ë×¢²á´¦Àí
 * @author zlsf
 *
 */
public class TerminalRegisterReqHandler implements InboundMessageHandler {

    private TerminalManagerService terminalManagerService;

    public void handle(Session session, JT808Packet packetData) {
	byte[] body = packetData.getMsgBodyBytes();
	TerminalRegister tr = new TerminalRegister(packetData.getTerminalId().toString());
	tr.setProvinceCode(ByteArrayUtil.getUnsignedShort(body, 0));
	tr.setCityCode(ByteArrayUtil.getUnsignedShort(body, 2));
	tr.setProducerId(ByteArrayUtil.toString(ByteArrayUtil.copyOfRange(body, 4, 9)));
	tr.setTerminalModel(ByteArrayUtil.toString(ByteArrayUtil.copyOfRange(body, 9, 29)));
	tr.setTerminalId(ByteArrayUtil.toString(ByteArrayUtil.copyOfRange(body, 29, 36)));
	tr.setVehicleColor(ByteArrayUtil.getUnsignedByte(body, 36));
	tr.setVehicleNo(ByteArrayUtil.toString(ByteArrayUtil.copyOfRange(body, 37, body.length)));
	//×¢²á
	TerminalRegisterResult result = terminalManagerService.terminalRegister(tr);

	TerminalRegisterRsp rsp = new TerminalRegisterRsp(packetData.getTerminalId());
	rsp.setAckMsgNo(packetData.getMsgNo());
	rsp.setResult(result);
	session.sendAckMessage(rsp);
    }

}
