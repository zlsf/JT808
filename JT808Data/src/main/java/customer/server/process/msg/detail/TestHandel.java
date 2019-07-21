package customer.server.process.msg.detail;

import com.kj.datacenter.msg.ZLSFTest;

import customer.server.model.PackData;
import customer.server.process.msg.AbstractMessage;

public class TestHandel extends AbstractMessage {

    public TestHandel(PackData data) {
	super(data);
    }

    @Override
    public void dealMsg() {
	ZLSFTest message=this.data.getEntity(ZLSFTest.class);
	System.out.println(this.data.getMessage().getMsgId());
	
    }

}
