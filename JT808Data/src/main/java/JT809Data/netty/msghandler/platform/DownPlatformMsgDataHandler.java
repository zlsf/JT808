package JT809Data.netty.msghandler.platform;

public interface DownPlatformMsgDataHandler {

	boolean canHandle(int dataType);

	void handle(DownPlatformMsgData mdata);
}
