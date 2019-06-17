package JT809Data.netty.msghandler.base;

public interface DownBaseMsgDataHandler {

	boolean canHandle(int dataType);
	
	void handle(DownBaseMsgData mdata);
}
