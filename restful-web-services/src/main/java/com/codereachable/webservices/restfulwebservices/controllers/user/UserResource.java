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

@RestController
public class UserResource {
 
	// Fields
	@Autowired
	private UserDaoService service;
	
	//GET /users
	// output -> retrieve all users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	//GET /users/{id}
	// input -> a user id
	// output -> return a specific user give an id
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User u = service.findOne(id);
		if (u == null) {
			/*
			 * When User object is null we throw a
			 * UserNotFoundException 
			 */
			throw new UserNotFoundException("id=" + id);
		}
		return u;
	}
	
	//DELETE /users/{id}
	// input -> a user id
	// output -> return a specific users give an id
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User u = service.deleteById(id);
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
	public ResponseEntity<Object> createUser(@Valid @RequestBody User u) {
		User savedUser = service.save(u);
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
}