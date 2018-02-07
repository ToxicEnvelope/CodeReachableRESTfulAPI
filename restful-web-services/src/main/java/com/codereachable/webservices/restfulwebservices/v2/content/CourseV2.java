package com.codereachable.webservices.restfulwebservices.v2.content;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection = "Courses")
@JsonIgnoreProperties({"active"})
public class CourseV2 {

	// Fields 
	@Id
	private String _courseId;  
	@NotNull
	@Size(min=2, max=10, message="Course Name excepts between 2 to 10 charachters")
	private String _courseName;
	@NotNull
	@Size(min=20, max=100, message="Course Details excepts between 20 to 100 charachters")
	private String _courseDetails;
	@NotNull
	private Integer _price;
	@NotNull
	private Boolean _isActive = false;
	
	// Default Constructor
	/*
	 * This default constructor is responsible of POST handling and User object creating
	 * Will handle user creation and not return a 500 response .
	 */
	public CourseV2() {}
	// Constructor
	public CourseV2(String id, String cname, String details, int price) {
		this._courseId = id;
		this._courseName = cname;
		this._courseDetails = details;
		this._price = price;	 
	}
	
	// Getters & Setters
	public String getId() {
		return _courseId;
	}
	
	public void setId(String id) {
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
	
	public Integer getCoursePrice() {
		return _price;
	}
	
	public void setCoursePrice(int price) {
		this._price = price;
	}
	
	public Boolean isActive() {
		return _isActive;
	}
	
	public void setCourseActivation(Boolean b) {
		this._isActive = b;
	}
	
	@Override
	public String toString() {
		return "Course : {id=" + getId() + ", course name=" + getCourseName() + ", course details=" + getCourseDetails() + ", price=" + getCoursePrice() + "}";
	}
}
