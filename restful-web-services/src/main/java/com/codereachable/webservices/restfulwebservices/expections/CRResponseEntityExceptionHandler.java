package com.codereachable.webservices.restfulwebservices.expections;


import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.codereachable.webservices.restfulwebservices.v1.content.CourseV1NotFoundException;
import com.codereachable.webservices.restfulwebservices.v1.controllers.user.UserV1NotFoundException;

import com.codereachable.webservices.restfulwebservices.v2.content.CourseV2NotFoundException;
import com.codereachable.webservices.restfulwebservices.v2.user.exceptions.UserV2NoContentException;
import com.codereachable.webservices.restfulwebservices.v2.user.exceptions.UserV2NotFoundException;
import com.codereachable.webservices.restfulwebservices.v2.user.exceptions.UserV2UnauthorizedException;

/*
 * This class will be responsible for 
 * all resources that being exposed.
 * Also, 
 * will provide a Response in case of exception
 * and will be applicable across all other controllers
 */
@ControllerAdvice
@RestController
public class CRResponseEntityExceptionHandler 
extends ResponseEntityExceptionHandler {
	
	// GENERIC ON ALL VERSIONS
	
	// Handles all exceptions 
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions
	(Exception ex, WebRequest req) {
		/*
		 * Creates a new instance of exception response
		 * provided by the following:
		 * 
		 * 	- current date
		 *  - the actual exception message
		 *  - set to false due to Security reasons, will be changed in the future to something more common
		 */
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(), 
				req.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// Handle arguments not valid
	public ResponseEntity<Object> handleMethodArgumentNotValid
	(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				"Validation Failed on session : " + req.getSessionId(), 
				ex.getBindingResult().getFieldError().toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	
	// ---- VERSION 1 ----
	
	// Handles UserV1NotFoundException
		@ExceptionHandler(UserV1NotFoundException.class)
		public final ResponseEntity<Object> handleUserExceptions
		(UserV1NotFoundException ex, WebRequest req) {
			ExceptionResponse exceptionResponse = new ExceptionResponse(
					new Date(),
					ex.getMessage(), 
					req.getDescription(false));
			return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
		}
		
		// Handles CourseV1NotFoundException
		@ExceptionHandler(CourseV1NotFoundException.class)
		public final ResponseEntity<Object> handleCourseExceptions
		(CourseV1NotFoundException ex, WebRequest req) {
			ExceptionResponse exceptionResponse = new ExceptionResponse(
					new Date(),
					ex.getMessage(), 
					req.getDescription(false));
			return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
		}

	
	// ---- VERSION 2 ---- 
	
	// Handles UserV2NotFoundException
	@ExceptionHandler(UserV2NotFoundException.class)
	public final ResponseEntity<Object> handleUserExceptions
	(UserV2NotFoundException ex, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(), 
				req.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	// Handles userV2NotAuthorizedException
	@ExceptionHandler(UserV2UnauthorizedException.class)
	public final ResponseEntity<Object> handleUserAuth
	(UserV2UnauthorizedException ex, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				req.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	
	// Handles CourseV2NotFoundException
	@ExceptionHandler(CourseV2NotFoundException.class)
	public final ResponseEntity<Object> handleCourseExceptions
	(CourseV2NotFoundException ex, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(), 
				req.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	// Handles UserV2NestedCoursesEmpty
	@ExceptionHandler(UserV2NoContentException.class)
	public final ResponseEntity<Object> handleCourseExceptions
	(UserV2NoContentException ex, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(), 
				req.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NO_CONTENT);
	}
}
