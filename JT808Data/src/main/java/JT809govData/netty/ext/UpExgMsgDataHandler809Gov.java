
package JT809govData.netty.ext;

public interface UpExgMsgDataHandler809Gov {

	boolean canHandle(int dataType);

	void handle(UpExgMsgData809Gov mdata);
}
