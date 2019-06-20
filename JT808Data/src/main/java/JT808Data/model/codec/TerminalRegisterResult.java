package JT808Data.model.codec;

/**
 * 终端注册结果
 * 
 * @author cuillgln
 *
 */
public class TerminalRegisterResult {

    private final int result;
    private final String authenticationKey;

    public TerminalRegisterResult(int result, String authenticationKey) {
	this.result = result;
	this.authenticationKey = authenticationKey;
    }

    public int getResult() {
	return result;
    }

    public String getAuthenticationKey() {
	return authenticationKey;
    }
}