package com.codereachable.webservices.restfulwebservices.content;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Course {

	
	// Fields 
	private Integer _courseId;
	@NotNull
	@Size(min=2, max=10, message="Course Name excepts between 2 to 10 charachters")
	private String _courseName;
	@NotNull
	@Size(min=20, max=100, message="Course Details excepts between 20 to 100 charachters")
	private String _courseDetails;
	private Boolean _isActive;
	
	// Default Constructor
	/*
	 * This default constructor is responsible of POST handling and User object creating
	 * Will handle user creation and not return a 500 response .
	 */
	protected Course() {}
	// Constructor
	public Course(Integer id, String cname, String details) {
		this._courseId = id;
		this._courseName = cname;
		this._courseDetails = details;
		this._isActive = false;	
	}
	
	// Getters & Setters
	public Integer getId() {
		return _courseId;
	}
	
	public void setId(Integer id) {
		this._courseId = id;
	}
	
	public String getCourseName() {
		return _courseName;
	}
	
	public void setCourseName(String cname) {
		this._courseName = cname;
	} 
	
	public String getCourseDetails() {
		return String.format("%s", _courseDetails);
	}
	
	public void setCourseDetails(String details) {
		this._courseDetails = String.format("%s", details);
	}
	
	public Boolean isActive() {
		return _isActive;
	}
	
	public void activateCourse(Boolean b) {
		this._isActive = b;
	}
	
	@Override
	public String toString() {
		return "User [id=" + _courseId + ", name=" + _courseName + ", course details=" + _courseDetails + "]";
	}
}
