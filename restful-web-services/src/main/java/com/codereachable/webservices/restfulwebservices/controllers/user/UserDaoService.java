package com.codereachable.webservices.restfulwebservices.controllers.user;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	// Fields
	private static List<User> users = new ArrayList<>();
	private static Integer usersCount = 3;
	
	// Static array list to simulate database
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
		/* TODO
		 *  FIX birthdate issue.
		 *  When a new User object is saved as followed:
		 *  --- JSON FORMAT ---
		 *  {
		 *  	"name" : "test user",
		 *  	"email" : "test_user@email.com",
		 *  	"birthdate" : "1990-10-011T23:19:59.942+0000"
		 *  } 
		 *  the birthdate appears to be NULL
		 *  */
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
	
	// deleteById -> find a specific user based on the id from DB
	public User deleteById(Integer id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User u = iterator.next();
			if (u.getId() == id) {
				iterator.remove();
				return u;
			}
		}
		return null;
	}

}
