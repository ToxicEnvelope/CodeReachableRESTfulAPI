package com.codereachable.webservices.restfulwebservices.v2.controllers.user;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.codereachable.webservices.restfulwebservices.v2.controllers.user.crypto.UserSecret;
import com.codereachable.webservices.restfulwebservices.v2.controllers.user.details.Alias;
import com.codereachable.webservices.restfulwebservices.v2.controllers.user.details.Details;

@Component
public class UserDaoServiceV2 {
	
	// Fields
	private static List<UserV2> users = new ArrayList<>();
	private static Integer usersCount = 3;
	
	// Static array list to simulate database
	static {
		users.add(new UserV2("1",new Details( new Alias("Adam", "Eden"), new Date(), "adam@yahoo.com", new UserSecret(""))));
		users.add(new UserV2("2", new Details( new Alias("Eve", "Eden"), new Date(), "eve@facebook.com", new UserSecret(""))));
		users.add(new UserV2("3", new Details( new Alias("Jack", "Sparro"), new Date(), "jack@live.com", new UserSecret(""))));
	}
	
	// findAll -> return all users from DB
	public List<UserV2> findAll() {
		return users;
	}
	
	// save -> save a new user to DB, and return his entity
	public UserV2 save(UserV2 u) {
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
			u.setId(Integer.toString(++usersCount));
		}
		if (u.getDetails().getDateOfBirth() == null) {
			u.getDetails().setDateOdBirth(new Date());
		}
		users.add(u);
		return u;
	}
	
	// findOne -> find a specific user based on the id from DB
	public UserV2 findById(String id) {
		for (UserV2 u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}
	
	// deleteById -> delete a specific user based on the id from DB
	public UserV2 deleteById(String id) {
		Iterator<UserV2> iterator = users.iterator();
		while(iterator.hasNext()) {
			UserV2 u = iterator.next();
			if (u.getId() == id) {
				iterator.remove();
				return u;
			}
		}
		return null;
	}

}
