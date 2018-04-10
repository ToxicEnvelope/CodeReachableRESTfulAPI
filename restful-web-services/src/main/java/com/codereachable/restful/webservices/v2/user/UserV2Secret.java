package com.codereachable.restful.webservices.v2.user;

import com.codereachable.restful.webservices.v2.crypto.Encryptor;

public class UserV2Secret {
	
	// Fields
	private String _key;
	private String _keyPhrase;
	
	public UserV2Secret() {}
	public UserV2Secret(String secret) {
		this._key = secret;
		this._keyPhrase = Encryptor.cryptWithMD5AndEncode(_key);
	}
	
	public String getKeyPhrase() {
		return _keyPhrase;
	}
	
	public String getKey() {
		return _key;
	}
	
	public void setKeyPhrase(String secret) {
		this._keyPhrase = Encryptor.cryptWithMD5AndEncode(secret);
	}
	
	public String toString() {
		return "UserSecret : {key=" + getKeyPhrase() + "}";
	}
}
