package com.codereachable.webservices.restfulwebservices.content;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CourseResource {
	
	// Fields
	@Autowired
	private CourseDaoService service;
	
	//GET /courses
	// output -> retrieve all courses
	@GetMapping("/courses")
	public List<Course> retrieveAllCourse() {
		return service.findAll();
	}
	
	//GET /courses/{id}
	// input -> a course id
	// output -> return a specific course given an id
	@GetMapping("/courses/{id}")
	public Course retrieveCourse(@PathVariable Integer id) {
		Course c = service.findOne(id);
		if (c == null) {
			/*
			 * When Course object is null we throw a 
			 * CourseNotFoundException
			 */
			throw new CourseNotFoundException("id=" + id);
		}
		return c;
	}
	
	//POST
	// input -> course object
	// output -> CREATED & return the current URI
	@PostMapping("/courses")
	public ResponseEntity<Object> createCourse(@Valid @RequestBody Course c) {
		Course savedCourse = service.save(c);
		/*  Build a URI object to represent the CREATED
		 * new Course and return his ResponseEntity
		 * 
		 *   /users/{new_user_id}
		 */
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedCourse.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
