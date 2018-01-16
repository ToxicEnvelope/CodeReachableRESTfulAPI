package com.codereachable.webservices.restfulservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
 
	@Autowired
	private UserDaoService service;
	
	//GET /users
	// retrieve all users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	//GET /users/{id}
	// return a specific users give an id
	@GetMapping("/users/{id}")
	public User retrieveSpecificUser(@PathVariable int id) {
		return service.findOne(id);
	}
	
}