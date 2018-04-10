package com.codereachable.restful.webservices.v2.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CourseDaoServiceV2 {

	// Fields
	private static List<CourseV2> courses = new ArrayList<>();
	private static Integer courseCount = 5;
	
	// Static array list to simulate database
	static {
		courses.add(new CourseV2("1", "Java", "A simple Java course", 175));
		courses.add(new CourseV2("2", "Python", "A simple Python course", 200));
		courses.add(new CourseV2("3", "JavaScript", "A simple JavaScript course", 150));
		courses.add(new CourseV2("4", "DOM", "A simple DOM course", 100));
		courses.add(new CourseV2("5", "Database", "A simple Database course", 125));
	}
	
	// findAll -> return all courses in DB
	public List<CourseV2> findAll() {
		return courses;
	}
	
	// save -> save a new course to DB, and return its entity
	public CourseV2 save(CourseV2 c) {
		if (c.getId() == null) {
			c.setId(Integer.toString(++courseCount));
		}
		if (c.isActive() == null) {
			c.setCourseActivation(false);
		}
		courses.add(c);
		return c;
	}
	
	// findOne -> find specific course based on the id from DB
	public CourseV2 findById(String id) {
		for(CourseV2 c : courses) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	// deleteById -> delete a specific user based on the id from DB
	public CourseV2 deleteById(String id) {
		Iterator<CourseV2> iterator = courses.iterator();
		while(iterator.hasNext()) {
			CourseV2 c = iterator.next();
			if (c.getId() == id) {
				iterator.remove();
				return c;
			}
		}
		return null;
	}
}
