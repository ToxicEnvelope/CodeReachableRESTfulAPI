package com.codereachable.webservices.restfulwebservices.controllers.user;

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

import com.codereachable.webservices.restfulwebservices.content.Course;
import com.codereachable.webservices.restfulwebservices.content.CourseDaoService;

@RestController
public class UserResource {
 
	// Fields
	@Autowired
	private UserDaoService service;
	@Autowired
	private CourseDaoService cservice;
	
	//GET /users
	// output -> retrieve all users
	@GetMapping("/users")
	public List<UserV2> retrieveAllUsers() {
		return service.findAll();
	}
	
	//GET /users/{id}
	// input -> a user id
	// output -> return a specific user give an id
	@GetMapping("/users/{id}")
	public UserV2 retrieveUser(@PathVariable int id) {
		UserV2 u = service.findById(id);
		if (u == null) {
			/*
			 * When User object is null we throw a
			 * UserNotFoundException 
			 */
			throw new UserNotFoundException("id=" + id);
		}
		return u;
	}
	
	//GET /users/{id}/course-list
	// input -> user id
	// output -> return all course from a specific user
	@GetMapping("/users/{id}/course-list")
	public List<Course> retriveUserCourses(@PathVariable int id) {
		UserV2 u = service.findById(id);
		if (u == null) {
			throw new UserNotFoundException("id=" + id);	
		}
		return u.getCourses();
	}
	
	//DELETE /users/{id}
	// input -> a user id
	// output -> return a specific users give an id
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		UserV2 u = service.deleteById(id);
		if (u == null) {
			/*
			 * When User object is null we throw a
			 * UserNotFoundException 
			 */
			throw new UserNotFoundException("id=" + id);
		}
	}
	
	//POST 
	// input -> user object
	// output -> CREATED & Return the current URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserV2 u) {
		UserV2 savedUser = service.save(u);
		/*  Build a URI object to represent the CREATED
		 * new User and return his ResponseEntity
		 * 
		 *   /users/{new_user_id}
		 */
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
		     	.path("/{id}")
		     	.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	/*
	 * TODO : Course is CREATED but response is 500
	 * FIX response CREATED issue.
	 * When 
	 */
	//POST
	// input -> course object
	// output -> CREATED & Return the current uri
	@PostMapping("/users/{uid}/add-course")
	public ResponseEntity<Object> addCourseToUser(@PathVariable int uid, @Valid @RequestBody Course c) {
		UserV2 currentUser = service.findById(uid);
		/*
		 * TODO : **Change the looping complexity**
		 * -- Search a course in smaller lists!
		 * -- get the cservice.findAll() -> List<Course> ,
		 * -- device to X of sublists and iterate through until matches found 
		 */
		for(Course dbcourse : cservice.findAll()) {
			if (c.getCourseName() == dbcourse.getCourseName()) {
				currentUser.setCourse(c);
			}
			else if (c.getCourseName() != dbcourse.getCourseName()) {
				currentUser.setCourse(cservice.save(c));
			}
		}
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
		     	.path("/{cid}")
		     	.buildAndExpand(currentUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}