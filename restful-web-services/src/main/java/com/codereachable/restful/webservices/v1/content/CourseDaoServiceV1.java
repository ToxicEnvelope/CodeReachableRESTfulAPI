package com.codereachable.restful.webservices.v1.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CourseDaoServiceV1 {

	// Fields
	private static List<CourseV1> courses = new ArrayList<>();
	private static Integer courseCount = 5;
	
	// Static array list to simulate database
	static {
		courses.add(new CourseV1(1, "Java", "A simple Java course"));
		courses.add(new CourseV1(2, "Python", "A simple Python course"));
		courses.add(new CourseV1(3, "JavaScript", "A simple JavaScript course"));
		courses.add(new CourseV1(4, "DOM", "A simple DOM course"));
		courses.add(new CourseV1(5, "Database", "A simple Database course"));
	}
	
	// findAll -> return all courses in DB
	public List<CourseV1> findAll() {
		return courses;
	}
	
	// save -> save a new course to DB, and return its entity
	public CourseV1 save(CourseV1 c) {
		if (c.getId() == null) {
			c.setId(++courseCount);
		}
		if (c.isActive() == null) {
			c.activateCourse(false);
		}
		courses.add(c);
		return c;
	}
	
	// findOne -> find specific course based on the id from DB
	public CourseV1 findById(int id) {
		for(CourseV1 c : courses) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	// deleteById -> delete a specific user based on the id from DB
	public CourseV1 deleteById(int id) {
		Iterator<CourseV1> iterator = courses.iterator();
		while(iterator.hasNext()) {
			CourseV1 c = iterator.next();
			if (c.getId() == id) {
				iterator.remove();
				return c;
			}
		}
		return null;
	}
}
