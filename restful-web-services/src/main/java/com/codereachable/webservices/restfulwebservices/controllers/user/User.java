package com.codereachable.webservices.restfulwebservices.controllers.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.codereachable.webservices.restfulwebservices.content.Course;

public class User {

	// Fields
	private Integer _id;
	@NotNull
	@Size(min=3, max=20, message="Name excepts between 3 to 20 charachters")
	private String _name;
	@Past(message="Date must be in the past")
	private Date _birthdate;
	// Optional custom @Pattern: 
	// https://stackoverflow.com/questions/4459474/hibernate-validator-email-accepts-askstackoverflow-as-valid
	@NotNull
	@Size(min=10, max=35, message="Email excepts between 10 to 35 charachters")
	@Email() 
	private String _email;
	@NotNull
	private Integer _score;
	@NotNull
	private List<Course> _courses = new ArrayList<>();
	// added global courses to simulate a DB for testing purposes
	{
		_courses.add(new Course(12, "C++", "A simple C++ description"));
		_courses.add(new Course(55, "GO", "A simple GO description"));
	}
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
		this._score = 0;
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
	
	public Integer getScore() {
		return _score;
	}
	
	public void setScore(Integer s) {
		this._score = s;
	}
	
	public List<Course> getCourses() {
		return _courses;
	}
	
	public void setCourse(Course c) {
		this._courses.add(c);
	}
	
	@Override
	public String toString() {
		return "User [id=" + _id + ", name=" + _name + ", birthdate=" + _birthdate + ", email=" + _email + ", course=" + _courses +"]";
	}
	

	
	
}
