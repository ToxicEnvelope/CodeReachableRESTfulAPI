package com.codereachable.restful.webservices.v2.user.account;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.codereachable.restful.webservices.v2.content.CourseV2;
import com.codereachable.restful.webservices.v2.user.account.details.Details;

public class Account {

	
	@NotNull
	private Details _details;
	@NotNull
	private Map<String, CourseV2> _courses = new HashMap<>();
	
	public Account() {}
	public Account(Details details) {
		this._details = details;
	}
	
	
	public Details getDetails() {
		return _details;
	}
	
	public void setDetails(Details details) {
		this._details = details;
	}
	
	public Map<String, CourseV2> getCourses() {
		return _courses;
	}
	
	public void addCourse(CourseV2 c) {
		
		this._courses.put(c.getId(),c);
	}
	
	public void removeCourse(CourseV2 c) {
		this._courses.remove(c.getId(),c);
	}
	
	@Override
	public String toString() {
		return "Account : {details="+ getDetails() +", courses="+ getCourses() +"}";
	}
}
