package com.codereachable.webservices.restfulwebservices.controllers.user.details;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

public class Details {

	@NotNull
	private Alias _alias;
	@Past
	private Date _bDate;
	@NotNull
	// Optional custom @Pattern: 
	// https://stackoverflow.com/questions/4459474/hibernate-validator-email-accepts-askstackoverflow-as-valid
	private String _email;
	@NotNull
	private Integer _score;
	
	public Details(String firstname, String lastname, Date date, String email) {
		this._alias = new Alias(firstname, lastname);
		this._bDate = date;
		this._email = email;
		this._score = 0;
	}
	
	// Getters & Setters
	public Alias getAlias() {
		return _alias;
	}
	
	public void setAlias(Alias alias) {
		this._alias = alias;
	}
	
	public Date getDateOfBirth() {
		return _bDate;
	}
	
	public void setDateOdBirth(Date date) {
		this._bDate = date;
	}
	
	public String getEmail() {
		return _email;
	}
	
	public void setEmail(String email) {
		this._email = email;
	}
	
	public Integer getScore() {
		return _score;
	}
	
	public void setScore(Integer s) {
		this._score = s;
	}
	
	public String toString() {
		return "[alias=" + getAlias() + ", birthdate=" + getDateOfBirth() +", email=" + getEmail() + "]";
	}
}
