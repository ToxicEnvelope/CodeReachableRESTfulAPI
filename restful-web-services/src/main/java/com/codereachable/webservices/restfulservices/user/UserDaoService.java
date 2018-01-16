package com.codereachable.webservices.restfulservices.user;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	private static Integer usersCount = 3;
	
	static {
		users.add(new User(1, "Adam", new Date(), "adam@yahoo.com"));
		users.add(new User(2, "Eve", new Date(), "eve@facebook.com"));
		users.add(new User(3, "Jack", new Date(), "jack@live.com"));
	}
	
	// findAll -> return all users from DB
	public List<User> findAll() {
		return users;
	}
	
	// save -> save a new user to DB, and return his entity
	public User save(User u) {
		if (u.getId() == null) {
			u.setId(++usersCount);
		}
		users.add(u);
		return u;
	}
	
	// findOne -> find a specific user based on the id from DB
	public User findOne(Integer id) {
		for (User u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}

}
