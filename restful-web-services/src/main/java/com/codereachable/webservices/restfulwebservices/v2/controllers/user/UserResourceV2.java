package com.codereachable.webservices.restfulwebservices.v2.controllers.user;

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

import com.codereachable.webservices.restfulwebservices.v2.content.CourseV2;
import com.codereachable.webservices.restfulwebservices.v2.utils.repositories.CourseV2Repository;
import com.codereachable.webservices.restfulwebservices.v2.utils.repositories.UserV2Repository;

@RestController
@RequestMapping("/v2/users")
public class UserResourceV2 {
 
	// Fields
//	@Autowired
//	private UserDaoServiceV2 service;
//	@Autowired
//	private CourseDaoServiceV2 cservice;
	@Autowired
	private UserV2Repository userRepository;
	@Autowired
	private CourseV2Repository courseRepository;
	
	//GET /users
	// output -> retrieve all users
	@GetMapping("/")
	public List<UserV2> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	//GET /users/{id}
	// input -> a user id
	// output -> return a specific user give an id
	@GetMapping("/{id}")
	public Optional<UserV2> retrieveUser(@PathVariable String id) {
		Optional<UserV2> u = userRepository.findById(id);
		if (!u.isPresent()) {
			/*
			 * When User object is null we throw a
			 * UserNotFoundException 
			 */
			throw new UserV2NotFoundException("id=" + id);
		}
		return u;
	}
	
	//GET /users/{id}/course-list
	// input -> user id
	// output -> return all course from a specific user
	@GetMapping("/{id}/course-list")
	public List<CourseV2> retriveUserCourses(@PathVariable String id) {
		Optional<UserV2> u = userRepository.findById(id);
		if (!u.isPresent()) {
			throw new UserV2NotFoundException("id=" + id);	
		}
		return u.get().getCourses();
	}
	
	//DELETE /users/{uid}/course-list/{cid}
	// input -> user id , course id
	// output -> delete a specific course of a specific user by id
	@DeleteMapping("/{uid}/course-list/{cid}")
	public void deleteUserCourse(@PathVariable String uid, @PathVariable String cid) {
		Optional<UserV2> u = userRepository.findById(uid);
		if (!u.isPresent()) {
			throw new UserV2NotFoundException("uid=" + uid);
		}
		else {
			List<CourseV2> userCourses = u.get().getCourses();
			for(CourseV2 c : userCourses) {
				if (cid == c.getId()) {
					userCourses.remove(c);
				}
			}
		}
	}
	
	//DELETE /users/{id}
	// input -> a user id
	// output -> return a specific users give an id
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable String id) {
		//UserV2 u = userRepository.deleteById(id);
		Optional<UserV2> u = userRepository.findById(id);
		if (!u.isPresent()) {
			/*
			 * When User object is null we throw a
			 * UserNotFoundException 
			 */
			throw new UserV2NotFoundException("id=" + id);
		}
		userRepository.deleteById(id);
	}
	
	//POST 
	// input -> user object
	// output -> CREATED & Return the current URI
	@PostMapping("/")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserV2 u) {
		UserV2 savedUser = userRepository.save(u);
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
	
	//PUT
	// input -> user id , course object
	// output -> UPDATED & Return the current uri
	@PutMapping("/{uid}/add-course")
	public ResponseEntity<Object> addCourseToUser(@PathVariable String uid, @Valid @RequestBody CourseV2 c) {
		Optional<UserV2> optional = Optional.empty();
		optional = userRepository.findById(uid);
		if (!optional.isPresent()) {
			throw new UserV2NotFoundException("id=" + uid);
		}
		UserV2 currentUser = optional.get();
		currentUser.addCourse(c);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{cid}")
		     	.buildAndExpand(currentUser).toUri();
		return ResponseEntity.created(uri).build();
	}
}