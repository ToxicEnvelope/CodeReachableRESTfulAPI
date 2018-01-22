package com.codereachable.webservices.restfulwebservices.v1.content;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CourseResourceV1 {
	
	// Fields
	@Autowired
	private CourseDaoServiceV1 service;
	
	//GET /courses
	// output -> retrieve all courses
	@GetMapping("/v1/courses")
	public List<CourseV1> retrieveAllCourse() {
		return service.findAll();
	}
	
	//GET /courses/{id}
	// input -> a course id
	// output -> return a specific course given an id
	@GetMapping("/v1/courses/{id}")
	public CourseV1 retrieveCourse(@PathVariable int id) {
		CourseV1 c = service.findById(id);
		if (c == null) {
			/*
			 * When Course object is null we throw a 
			 * CourseNotFoundException
			 */
			throw new CourseV1NotFoundException("id=" + id);
		}
		return c;
	}
	
	//DELETE /courses/{id}
	// input -> a course id
	// output -> return a specific course given an id
	@DeleteMapping("/v1/courses/{id}")
	public void deleteCourse(@PathVariable int id) {
		CourseV1 c = service.deleteById(id);
		if (c == null) {
			/*
			 * When Course object is null we throw a 
			 * CourseNotFoundException
			 */
			throw new CourseV1NotFoundException("id=" + id);
		}
	}
	
	//POST
	// input -> course object
	// output -> CREATED & return the current URI
	@PostMapping("/v1/courses")
	public ResponseEntity<Object> createCourse(@Valid @RequestBody CourseV1 c) {
		CourseV1 savedCourse = service.save(c);
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
