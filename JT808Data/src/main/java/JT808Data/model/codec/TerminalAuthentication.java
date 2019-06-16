package JT808Data.model.codec;

/**
 * ¼øÈ¨
 * 
 * @author cuillgln
 *
 */
public class TerminalAuthentication {

    private String terminalPhone;
    private String authenticationKey;

    public TerminalAuthentication(String terminalPhone) {
	this.terminalPhone = terminalPhone;
    }

    public String getTerminalPhone() {
	return terminalPhone;
    }

    public String getAuthenticationKey() {
	return authenticationKey;
    }

    public void setAuthenticationKey(String authenticationKey) {
	this.authenticationKey = authenticationKey;
    }

    @Override
    public String toString() {
	return "TerminalAuthentication [terminalPhone=" + terminalPhone + ", authenticationKey=" + authenticationKey
		+ "]";
    }
    
}