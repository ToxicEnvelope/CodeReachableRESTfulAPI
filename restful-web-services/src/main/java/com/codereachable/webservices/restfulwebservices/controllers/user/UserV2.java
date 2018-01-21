package com.codereachable.webservices.restfulwebservices.controllers.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.codereachable.webservices.restfulwebservices.content.Course;
import com.codereachable.webservices.restfulwebservices.controllers.user.details.Details;

public class UserV2 {

	// Fields
	private Integer _id;
	@NotNull
	private Details _details;
	@NotNull
	private List<Course> _courses = new ArrayList<>();
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
	public UserV2(Integer id, String firstName, String lastName, Date date, String email) {
		this._id = id;
		this._details = new Details(firstName, lastName, date, email);
	}

	// Getters & Setters
	public Integer getId() {
		return _id;
	}

	public void setId(Integer id) {
		this._id = id;
	}
	
	public Details getDetails() {
		return _details;
	}
	
	public void setDetails(Details details) {
		this._details = details;
	}
	
	public List<Course> getCourses() {
		return _courses;
	}
	
	public void setCourse(Course c) {
		this._courses.add(c);
	}
	
	@Override
	public String toString() {
		return "User [id=" + getId() + ", details=" + getDetails() + ", course=" + getCourses() +"]";
	}
}
