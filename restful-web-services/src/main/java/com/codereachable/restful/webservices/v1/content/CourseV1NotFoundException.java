package com.codereachable.restful.webservices.v1.content;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Added @ResponseStatus NOT_FOUND due to 
// response 500 Internal Server Error when UserNotFoundException is called
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CourseV1NotFoundException extends RuntimeException {
	// Fields
	// declared a serialVersionUID due to superclass serialization warning
	private static final long serialVersionUID = 1L;
	// Constructor
	public CourseV1NotFoundException(String message) {
		super(message);
	}
}
