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

import com.codereachable.webservices.restfulwebservices.content.CourseNotFoundException;
import com.codereachable.webservices.restfulwebservices.controllers.user.UserNotFoundException;

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
	
	// Handles UserNotFoundException
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserExceptions
	(UserNotFoundException ex, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(), 
				req.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	// Handles CourseNotFoundException
	@ExceptionHandler(CourseNotFoundException.class)
	public final ResponseEntity<Object> handleCourseExceptions
	(CourseNotFoundException ex, WebRequest req) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(), 
				req.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}
