package customer.util;

import com.alibaba.fastjson.JSON;
import com.kj.datacenter.msg.BaseMessage;

import customer.server.model.PackData;

public class PacketCodec {
    public static PackData convertToBaseMessageVO(String json) {
	try {
	    BaseMessage vo = JSON.parseObject(json, BaseMessage.class);
	    PackData data = new PackData(vo, null);
	    return data;
	} catch (Exception ex) {
	    return null;
	}
    }

}
