package com.codereachable.webservices.restfulwebservices.expections;

import java.util.Date;

/*
 * This Class will handle a generic Exception structure 
 * for all future exceptions and provide some information
 */
public class ExceptionResponse {

	//Fields
	private Date _timestamp;
	private String _message;
	private String _details;
	
	// Constructor
	public ExceptionResponse(Date t, String m, String d) {
		this._timestamp = t;
		this._message = m;
		this._details = d;
	}
	
	// Getters ONLY - Setters are not needed due to a generic structure
	public Date getTimestamp() {
		return _timestamp;
	}
	
	public String getMessage() {
		return this._message;
	}
	
	public String getDetails() {
		return _details;
	}
	
}
