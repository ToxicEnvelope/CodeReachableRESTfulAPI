package com.codereachable.webservices.restfulwebservices.sessions;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.data.mongodb.core.mapping.Document;

import com.codereachable.webservices.restfulwebservices.v2.user.UserV2;

@Document( collection = "Sessions" )
public class SessionManager {
	
	// Fields
	private Logger log = Logger.getLogger(this.getClass().getName());
	private Map<Date, String> sessions = new HashMap<Date, String>();
	
	public SessionManager() {}
	
	public void addSession(UserV2 user) {
		sessions.put(new Date(), user.getId());
		log.info("New session has been initialized from -> " + user.getId());
	}

}
