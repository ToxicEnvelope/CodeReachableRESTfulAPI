package com.codereachable.webservices.restfulwebservices.v1.controllers.user;

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

import com.codereachable.webservices.restfulwebservices.v1.content.CourseDaoServiceV1;
import com.codereachable.webservices.restfulwebservices.v1.content.CourseV1;

@RestController
public class UserResourceV1 {
 
	// Fields
	@Autowired
	private UserDaoServiceV1 service;
	@Autowired
	private CourseDaoServiceV1 cservice;
	
	//GET /users
	// output -> retrieve all users
	@GetMapping("/v1/users")
	public List<UserV1> retrieveAllUsers() {
		return service.findAll();
	}
	
	//GET /users/{id}
	// input -> a user id
	// output -> return a specific user give an id
	@GetMapping("/v1/users/{id}")
	public UserV1 retrieveUser(@PathVariable int id) {
		UserV1 u = service.findById(id);
		if (u == null) {
			/*
			 * When User object is null we throw a
			 * UserNotFoundException 
			 */
			throw new UserV1NotFoundException("id=" + id);
		}
		return u;
	}
	
	//GET /users/{id}/course-list
	// input -> user id
	// output -> return all course from a specific user
	@GetMapping("/v1/users/{id}/course-list")
	public List<CourseV1> retriveUserCourses(@PathVariable int id) {
		UserV1 u = service.findById(id);
		if (u == null) {
			throw new UserV1NotFoundException("id=" + id);	
		}
		return u.getCourses();
	}
	
	//DELETE /users/{uid}/course-list/{cid}
	// input -> user id , course id
	// output -> delete a specific course of a specific user by id
	@DeleteMapping("/v1/users/{uid}/course-list/{cid}")
	public void deleteUserCourse(@PathVariable int uid, @PathVariable int cid) {
		UserV1 u = service.findById(uid);
		if (u == null) {
			throw new UserV1NotFoundException("uid=" + uid);
		}
		else {
			List<CourseV1> userCourses = u.getCourses();
			for(CourseV1 c : userCourses) {
				if (cid == c.getId()) {
					userCourses.remove(c);
				}
			}
		}
	}
	
	//DELETE /users/{id}
	// input -> a user id
	// output -> return a specific users give an id
	@DeleteMapping("/v1/users/{id}")
	public void deleteUser(@PathVariable int id) {
		UserV1 u = service.deleteById(id);
		if (u == null) {
			/*
			 * When User object is null we throw a
			 * UserNotFoundException 
			 */
			throw new UserV1NotFoundException("id=" + id);
		}
	}
	
	//POST 
	// input -> user object
	// output -> CREATED & Return the current URI
	@PostMapping("/v1/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserV1 u) {
		UserV1 savedUser = service.save(u);
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
	@PostMapping("/v1/users/{uid}/add-course")
	public ResponseEntity<Object> addCourseToUser(@PathVariable int uid, @Valid @RequestBody CourseV1 c) {
		UserV1 currentUser = service.findById(uid);
		/*
		 * TODO : **Change the looping complexity**
		 * -- Search a course in smaller lists!
		 * -- get the cservice.findAll() -> List<Course> ,
		 * -- device to X of sublists and iterate through until matches found 
		 */
		for(CourseV1 dbcourse : cservice.findAll()) {
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