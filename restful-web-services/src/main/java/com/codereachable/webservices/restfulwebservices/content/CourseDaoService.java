package com.codereachable.webservices.restfulwebservices.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CourseDaoService {

	// Fields
	private static List<Course> courses = new ArrayList<>();
	private static Integer courseCount = 5;
	
	// Static array list to simulate database
	static {
		courses.add(new Course(1, "Java", "A simple Java course"));
		courses.add(new Course(2, "Python", "A simple Python course"));
		courses.add(new Course(3, "JavaScript", "A simple JavaScript course"));
		courses.add(new Course(4, "DOM", "A simple DOM course"));
		courses.add(new Course(5, "Database", "A simple Database course"));
	}
	
	// findAll -> return all courses in DB
	public List<Course> findAll() {
		return courses;
	}
	
	// save -> save a new course to DB, and return its entity
	public Course save(Course c) {
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
	public Course findOne(Integer id) {
		for(Course c : courses) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	// deleteById -> delete a specific user based on the id from DB
	public Course deleteById(Integer id) {
		Iterator<Course> iterator = courses.iterator();
		while(iterator.hasNext()) {
			Course c = iterator.next();
			if (c.getId() == id) {
				iterator.remove();
				return c;
			}
		}
		return null;
	}
}
