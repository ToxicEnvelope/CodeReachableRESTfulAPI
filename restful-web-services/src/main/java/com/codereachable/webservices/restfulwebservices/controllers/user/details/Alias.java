package com.codereachable.webservices.restfulwebservices.controllers.user.details;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Alias {

	@NotNull
	@Size(min=3, max=10)
	private String _first;
	@NotNull
	@Size(min=6, max=15)
	private String _last;
	
	public Alias(String fname, String lname) {
		this._first = fname;
		this._last = lname;
	}
	
	// Getters & Setters
	public String getFirstName() {
		return _first;
	}
	
	public void setFirstName(String fn) {
		this._first = fn;
	}
	
	public String getLastName() {
		return _last;
	}
	
	public void setLastName(String ln) {
		this._last = ln;
	}
	
	public String toString() {
		return "[firstname=" + getFirstName() + ", lastname=" + getLastName() + "]";
	}
}
