package com.codereachable.restful.webservices.v2.user;

import com.codereachable.restful.webservices.v2.crypto.Encryptor;


public class UserV2Secret {
	
	// Fields
	private String _secret;
	private String _keyPhrase;
	
	public UserV2Secret() {}
	public UserV2Secret(String secret) {
		this._secret = secret;
		setKeyPhrase(_secret);
	}
	
	public String getKeyPhrase() {
		return _keyPhrase;
	}
	
	public String getSecret() {
		return _secret;
	}
	
	public void setKeyPhrase(String secret) {
		this._keyPhrase = Encryptor.cryptWithMD5AndEncode(secret);
	}
	
	public String toString() {
		return "UserSecret : {key=" + getKeyPhrase() + "}";
	}
}
