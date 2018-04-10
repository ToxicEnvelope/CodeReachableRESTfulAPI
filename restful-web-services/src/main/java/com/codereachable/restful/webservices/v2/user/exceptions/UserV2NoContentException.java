package com.codereachable.restful.webservices.v2.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Added @ResponseStatus NO_CONTENT due to 
//response 500 Internal Server Error when UserNotFoundException is called
@ResponseStatus(HttpStatus.NO_CONTENT)
public class UserV2NoContentException extends RuntimeException {
	// Fields
	// declared a serialVersionUID due to superclass serialization warning
	private static final long serialVersionUID = 1L;
	// Constructor
	public UserV2NoContentException(String message) {
		super(message);
	}
}
