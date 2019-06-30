
package customer.server.process.msg.detail;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.kj.datacenter.msg.LoginAnswerMsg;

import customer.server.model.PackData;
import customer.server.process.MessageSender;
import customer.server.process.msg.AbstractMessage;

public class LoginMessage extends AbstractMessage {

    Logger log = LoggerFactory.getLogger(LoginMessage.class);

    public LoginMessage(PackData data) {
	super(data);
    }

    @Override
    public void dealMsg() {
	try {
	    LoginAnswerMsg msg = new LoginAnswerMsg(true);
	    try {
		MessageSender.getInstance().sendToDevice(this.data.getChannel(),
			JSON.toJSONString(msg).getBytes("UTF-8"));
	    } catch (UnsupportedEncodingException e) {

	    }
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }
}
