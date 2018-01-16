package com.codereachable.webservices.restfulservices.user;

import java.util.Date;

public class User {

	private Integer _id;
	private String _name;
	private Date _birthdate;
	private String _email;
	
	
	public User(Integer id, String name, Date date, String email) {
		this._id = id;
		this._name = name;
		this._birthdate = date;
		this._email = email;
	}


	public Integer getId() {
		return _id;
	}

	public void setId(Integer _id) {
		this._id = _id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
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

	public void setEmail(String _email) {
		this._email = _email;
	} 

	@Override
	public String toString() {
		return "User [_id=" + _id + ", _name=" + _name + ", _birthdate=" + _birthdate + ", _email=" + _email + "]";
	}
	

	
	
}
