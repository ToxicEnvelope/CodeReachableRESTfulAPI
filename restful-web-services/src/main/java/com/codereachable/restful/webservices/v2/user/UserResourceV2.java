package com.codereachable.restful.webservices.v2.user;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.codereachable.restful.webservices.v2.content.CourseV2;
import com.codereachable.restful.webservices.v2.content.exceptions.CourseV2NotFoundException;
import com.codereachable.restful.webservices.v2.user.exceptions.UserV2NoContentException;
import com.codereachable.restful.webservices.v2.user.exceptions.UserV2NotFoundException;
import com.codereachable.restful.webservices.v2.user.exceptions.UserV2UnauthorizedException;
import com.codereachable.restful.webservices.v2.utils.repositories.CourseV2Repository;
import com.codereachable.restful.webservices.v2.utils.repositories.UserV2Repository;

@RestController
@RequestMapping("/v2")
@Transactional
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
	@GetMapping("/users")
	public List<UserV2> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	//GET /users/{id}
	// input -> a user id
	// output -> return a specific user give an id
	@GetMapping("/users/{id}")
	public UserV2 retrieveUser(@PathVariable String id) {
		Optional<UserV2> optionalUser  = Optional.empty(); 
		optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			/*
			 * When User object is null we throw a
			 * UserNotFoundException 
			 */
			throw new UserV2NotFoundException("id=" + id);
		}
		UserV2 currentUser = optionalUser.get();
		return currentUser;
	}
	
	//GET /users/{id}/courses-list
	// input -> user id
	// output -> return all course from a specific user
	@GetMapping("/users/{id}/courses-list")
	public List<CourseV2> retriveUserCourses(@PathVariable String id) {
		Optional<UserV2> u = userRepository.findById(id);
		if (!u.isPresent()) {
			throw new UserV2NotFoundException("id=" + id);	
		}
		return u.get().getCourses();
	}
	
	//DELETE /users/{uid}/remove-course/{cid}
	// input -> user id , course id
	// output -> delete a specific course of a specific user by id
	@DeleteMapping("/users/{uid}/remove-course/{cid}")
	public void deleteUserCourse(@PathVariable String uid, @PathVariable String cid) {
		Optional<UserV2> optionalUser = Optional.empty();
		optionalUser = userRepository.findById(uid);
		if (!optionalUser.isPresent()) {
			throw new UserV2NotFoundException("uid=" + uid);
		}
		// retrieve user object 
		UserV2 actualUser = optionalUser.get();
		// retrieve courses list
		List<CourseV2> userCourses = actualUser.getCourses();
		if (userCourses.isEmpty()) {
			throw new UserV2NoContentException("uid=" + uid);
		}
		for(Iterator<CourseV2> iterator = userCourses.iterator(); iterator.hasNext();) {
			CourseV2 c = iterator.next();
			if (c.getId().equals(cid)) {
				iterator.remove();
			}
		} 
		userRepository.save(actualUser);
	}
	
	//DELETE /users/{id}
	// input -> a user id
	// output -> return a specific users give an id
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable String id) {
		//UserV2 u = userRepository.deleteById(id);
		Optional<UserV2> optionalUser = Optional.empty();
		optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			/*
			 * When User object is null we throw a
			 * UserNotFoundException 
			 */
			throw new UserV2NotFoundException("id=" + id);
		}
		userRepository.deleteById(id);
	}
	
	//PUT /users/{uid}/add-course
	// input -> user id , course object
	// output -> UPDATED & Return the current uri
	@PutMapping("/users/{uid}/add-course")
	public ResponseEntity<Object> addCourseToUser(@PathVariable String uid, @Valid @RequestBody CourseV2 c) {
		// Optional User
		Optional<UserV2> optionalUser = Optional.empty();
		optionalUser = userRepository.findById(uid);
		// Optional Course
		Optional<CourseV2> optionalCourse = Optional.empty();
		optionalCourse = courseRepository.findById(c.getId());
		// User check in DB
		if (!optionalUser.isPresent()) {
			throw new UserV2NotFoundException("id=" + uid);
		}
		// return a User object from DB 
		UserV2 currentUser = optionalUser.get();
		// Course check in DB
		if (!optionalCourse.isPresent()) {
			throw new CourseV2NotFoundException("id=" + c.getId());
		}
		// Add course to user
		currentUser.addCourse(c);
		// Save DB changes
		userRepository.save(currentUser);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{cid}")
		     	.buildAndExpand(currentUser).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// REGISTRATION SECTION //

	//POST /register
	// input -> user object
	// output -> CREATED & Return the current URI
	@PostMapping("/register")
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
	
	// LOGIN SECTION //
	
	//GET /login/{email}/{secret}
	// input -> email , secret
	// output -> return a User object
	@GetMapping("/login/{email}/{secret}")
	public UserV2 makeLogin(@PathVariable String email, @PathVariable String secret) {
		UserV2 targetUser = null;
		List<UserV2> users = userRepository.findAll();
		// Looking for the correct user in users by his email
		for(UserV2 u : users) {
			if (u.getDetails().getEmail().equals(email)) {
				targetUser = u;
				break;
			}
		}
		// Check for user's key 
		if (!targetUser.getDetails().getSecret().getSecret().equals(secret)) {
			throw new UserV2UnauthorizedException("password=" + secret); 
		}
		return targetUser;
	}
}