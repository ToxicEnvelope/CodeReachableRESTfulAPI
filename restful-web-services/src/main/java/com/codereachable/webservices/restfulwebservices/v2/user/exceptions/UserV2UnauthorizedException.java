package com.codereachable.webservices.restfulwebservices.v2.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserV2UnauthorizedException extends RuntimeException {
	// Fields
	// declared a serialVersionUID due to superclass serialization warning
	private static final long serialVersionUID = 1L;
	// Constructor
	public UserV2UnauthorizedException(String message) {
		super(message);
	}
}
