package com.codereachable.webservices.restfulwebservices.v2.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Added @ResponseStatus NOT_FOUND due to 
// response 500 Internal Server Error when UserNotFoundException is called
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserV2NotFoundException extends RuntimeException {
	// Fields
	// declared a serialVersionUID due to superclass serialization warning
	private static final long serialVersionUID = 1L;
	// Constructor
	public UserV2NotFoundException(String message) {
		super(message);
	}
}
