package com.codereachable.restful.webservices.v2.content;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.codereachable.restful.webservices.v2.content.exceptions.CourseV2NotFoundException;
import com.codereachable.restful.webservices.v2.utils.repositories.CourseV2Repository;

@RestController
@RequestMapping("/v2")
public class CourseResourceV2 {
	
	// Fields
//	@Autowired
//	private CourseDaoServiceV2 service;
	@Autowired
	private CourseV2Repository courseRepository;
	
	
	//GET /courses
	// output -> retrieve all courses
	@GetMapping("/courses")
	public List<CourseV2> retrieveAllCourse() {
		return courseRepository.findAll();
	}
	
	//GET /courses/{id}
	// input -> a course id
	// output -> return a specific course given an id
	@GetMapping("/courses/{id}")
	public Optional<CourseV2> retrieveCourse(@PathVariable String id) {
		Optional<CourseV2> c = courseRepository.findById(id);
		if (!c.isPresent()) {
			/*
			 * When Course object is null we throw a 
			 * CourseNotFoundException
			 */
			throw new CourseV2NotFoundException("id=" + id);
		}
		return c;
	}
	
	//DELETE /courses/{id}
	// input -> a course id
	// output -> return a specific course given an id
	@DeleteMapping("/courses/{id}")
	public void deleteCourse(@PathVariable String id) {
		Optional<CourseV2> c = courseRepository.findById(id);
		if (!c.isPresent()) {
			/*
			 * When Course object is null we throw a 
			 * CourseNotFoundException
			 */
			throw new CourseV2NotFoundException("id=" + id);
		}
		courseRepository.deleteById(id);
	}
	
	//POST
	// input -> course object
	// output -> CREATED & return the current URI
	@PostMapping("/courses")
	public ResponseEntity<Object> createCourse(@Valid @RequestBody CourseV2 c) {
		CourseV2 savedCourse = courseRepository.save(c);
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
	
	//PUT 
	// input -> course id
	// output -> course object
	@PutMapping("/courses/{id}")
	public ResponseEntity<Object> updateCourse(@PathVariable String id, @Valid @RequestBody CourseV2 c) {
		Optional<CourseV2> optionalCourse = Optional.empty();
		optionalCourse = courseRepository.findById(id);
		if (!optionalCourse.isPresent()) {
			throw new CourseV2NotFoundException("id=" + id);
		}
		CourseV2 currerntCourse = optionalCourse.get();
		courseRepository.delete(currerntCourse);
		CourseV2 savedCourse = courseRepository.save(c);
		URI uri  = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.buildAndExpand(savedCourse.getId()).toUri();
		return ResponseEntity.created(uri).build();
				
		
	}
}
