package com.codereachable.webservices.restfulwebservices.v2.controllers.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.codereachable.webservices.restfulwebservices.v2.content.CourseV2;
import com.codereachable.webservices.restfulwebservices.v2.controllers.user.details.Details;

@Document( collection = "Users")
public class UserV2 {

	// Fields
	@Id
	private String _id;
	@NotNull
	private Details _details;
	@NotNull
	private List<CourseV2> _courses = new ArrayList<>();
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
	protected UserV2() {}
	//Constructor
	public UserV2(String i, String firstName, String lastName, Date date, String email) {
		this._id = i;
		this._details = new Details(firstName, lastName, date, email);
	}

	// Getters & Setters
	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}
	
	public Details getDetails() {
		return _details;
	}
	
	public void setDetails(Details details) {
		this._details = details;
	}
	
	public List<CourseV2> getCourses() {
		return _courses;
	}
	
	public void setCourse(CourseV2 c) {
		this._courses.add(c);
	}
	
	@Override
	public String toString() {
		return "User [id=" + getId() + ", details=" + getDetails() + ", course=" + getCourses() +"]";
	}
}
