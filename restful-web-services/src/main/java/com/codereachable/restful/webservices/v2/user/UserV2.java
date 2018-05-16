package com.codereachable.restful.webservices.v2.user;


import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import com.codereachable.restful.webservices.v2.user.account.Account;
@Service
@Document( collection = "Users")
public class UserV2 {

	// Fields
	@Id
	private String _id;
	@NotNull
	private Account _account;
	
	//private List<CourseV2> _courses = new ArrayList<>();
//	// added global courses to simulate a DB for testing purposes
//	{
//		_courses.add(new Course(12, "C++", "A simple C++ description"));
//		_courses.add(new Course(55, "GO", "A simple GO description"));
//	}
	// Default Constructor 
	/*
	 * This default constructor is responsible of POST handling and User object creating
	 * Will handle user creation and not return a 500 response .
	 */
	public UserV2() {}
	public UserV2(Account account) {
		this._id = UUID.randomUUID().toString().replace("-", ".");
		this._account = account;
	}

	// Getters & Setters
	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}

	public Account getAccount() {
		return _account;
	}
	
	public void setAccount(Account account) {
		this._account = account;
	}
	
	@Override
	public String toString() {
		return "User : {id=" + getId()+", account="+ getAccount() +"}";
	}
}
