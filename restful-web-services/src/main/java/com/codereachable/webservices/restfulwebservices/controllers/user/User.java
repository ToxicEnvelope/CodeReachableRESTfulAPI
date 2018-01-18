package com.codereachable.webservices.restfulwebservices.controllers.user;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {

	// Fields
	private Integer _id;
	@NotNull
	@Size(min=3, max=20)
	private String _name;
	@Past
	private Date _birthdate;
	@NotNull
	@Size(min=10, max=35)
	@Email()
	private String _email;
	
	// Default Constructor 
	/*
	 * This default constructor is responsible of POST handling and User object creating
	 * Will handle user creation and not return a 500 response .
	 */
	protected User() {}
	//Constructor
	public User(Integer id, String name, Date date, String email) {
		this._id = id;
		this._name = name;
		this._birthdate = date;
		this._email = email;
	}

	// Getters & Setters
	public Integer getId() {
		return _id;
	}

	public void setId(Integer id) {
		this._id = id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public Date getBirthdate() {
		return _birthdate;
	}

	public void setBrithdate(Date date) {
		this._birthdate = date;
	}


	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		this._email = email;
	} 

	@Override
	public String toString() {
		return "User [_id=" + _id + ", _name=" + _name + ", _birthdate=" + _birthdate + ", _email=" + _email + "]";
	}
	

	
	
}