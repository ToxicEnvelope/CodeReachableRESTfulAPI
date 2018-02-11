package com.codereachable.webservices.restfulwebservices.v2.user;

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
import com.codereachable.webservices.restfulwebservices.v2.content.CourseV2NotFoundException;
import com.codereachable.webservices.restfulwebservices.v2.user.exceptions.UserV2NotFoundException;
import com.codereachable.webservices.restfulwebservices.v2.user.exceptions.UserV2UnauthorizedException;
import com.codereachable.webservices.restfulwebservices.v2.utils.repositories.CourseV2Repository;
import com.codereachable.webservices.restfulwebservices.v2.utils.repositories.UserV2Repository;

@RestController
@RequestMapping("/v2")
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
	
	//GET /users/{id}/course-list
	// input -> user id
	// output -> return all course from a specific user
	@GetMapping("/users/{id}/course-list")
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
	@DeleteMapping("/users/{uid}/course-list/{cid}")
	public void deleteUserCourse(@PathVariable String uid, @PathVariable String cid) {
		Optional<UserV2> optionalUser = Optional.empty();
		optionalUser = userRepository.findById(uid);
		if (!optionalUser.isPresent()) {
			throw new UserV2NotFoundException("uid=" + uid);
		}
		else {
			List<CourseV2> userCourses = optionalUser.get().getCourses();
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
	
	//POST 
	// input -> user object
	// output -> CREATED & Return the current URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserV2 u) {
		UserV2 savedUser = userRepository.save(u);
		/*  Build a URI object to represent the CREATED
		 * new User and return his ResponseEntity
		 * 
		 *   /users/{new_user_id}
		 */
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
		     	.path("/users/{id}")
		     	.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//PUT
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
	
	// LOGIN SECTION //
	
	//GET 
	// input -> email , key
	// output -> return a User object
	@GetMapping("/login/{email}/{key}")
	public UserV2 makeLogin(@PathVariable String email, @PathVariable String key) {
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
		if (!targetUser.getDetails().getSecret().getKey().equals(key)) {
			throw new UserV2UnauthorizedException("password=" + key); 
		}
		return targetUser;
	}
}