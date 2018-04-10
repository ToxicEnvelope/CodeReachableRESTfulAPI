package com.codereachable.restful.webservices.v1.controllers.user;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoServiceV1 {
	
	// Fields
	private static List<UserV1> users = new ArrayList<>();
	private static Integer usersCount = 3;
	
	// Static array list to simulate database
	static {
		users.add(new UserV1(1, "Adam Eden", new Date(), "adam@yahoo.com"));
		users.add(new UserV1(2, "Eve Eden", new Date(), "eve@facebook.com"));
		users.add(new UserV1(3, "Jack Sparrow", new Date(), "jack@live.com"));
	}
	
	// findAll -> return all users from DB
	public List<UserV1> findAll() {
		return users;
	}
	
	// save -> save a new user to DB, and return his entity
	public UserV1 save(UserV1 u) {
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
		if (u.getBirthdate() == null) {
			u.setBrithdate(new Date());
		}
		users.add(u);
		return u;
	}
	
	// findOne -> find a specific user based on the id from DB
	public UserV1 findById(int id) {
		for (UserV1 u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}
	
	// deleteById -> delete a specific user based on the id from DB
	public UserV1 deleteById(int id) {
		Iterator<UserV1> iterator = users.iterator();
		while(iterator.hasNext()) {
			UserV1 u = iterator.next();
			if (u.getId() == id) {
				iterator.remove();
				return u;
			}
		}
		return null;
	}

}
