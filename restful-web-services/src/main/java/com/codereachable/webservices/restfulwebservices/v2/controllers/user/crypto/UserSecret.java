package com.codereachable.webservices.restfulwebservices.v2.controllers.user.crypto;

public class UserSecret {
	
	// Fields
	private String _keyPhrase;
	
	public UserSecret() {}
	public UserSecret(String secret) {
		this._keyPhrase = PasswordEncryptor.cryptWithMD5AndEncode(secret);
	}
	
	public String getKeyPhrase() {
		return _keyPhrase;
	}
	
	public void setKeyPhrase(String secret) {
		this._keyPhrase = secret;
	}
	
	public String toString() {
		return "UserSecret : {key=" + getKeyPhrase() + "}";
	}
}
