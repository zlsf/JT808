package customer.server.process.msg.detail;

import customer.server.model.PackData;
import customer.server.process.msg.AbstractMessage;

public class TestHandel extends AbstractMessage {

    public TestHandel(PackData data) {
	super(data);
    }

    @Override
    public void dealMsg() {
	System.out.println(this.data.getMessage().getMsgId());
    }

}
