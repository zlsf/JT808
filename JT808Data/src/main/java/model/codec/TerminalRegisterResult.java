package com.haiyisoft.jt808.domain.model;

/**
 * ÖÕ¶Ë×¢²á½á¹û
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
