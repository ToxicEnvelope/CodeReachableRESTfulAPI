package com.codereachable.webservices.restfulwebservices.v2.controllers.user;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import com.codereachable.webservices.restfulwebservices.v2.content.CourseV2;
import com.codereachable.webservices.restfulwebservices.v2.controllers.user.details.Details;
@Service
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
	public UserV2() {}
	//Constructor
	public UserV2(String id, Details details) {
		this._id = id;
		this._details = details;
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
		return "[id=" + getId() + ", details=" + getDetails() + ", course=" + getCourses() +"]";
	}
}
