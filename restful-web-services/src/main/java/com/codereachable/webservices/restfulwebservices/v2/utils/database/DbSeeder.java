package com.codereachable.webservices.restfulwebservices.v2.utils.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codereachable.webservices.restfulwebservices.v2.content.CourseV2;
import com.codereachable.webservices.restfulwebservices.v2.user.UserV2;
import com.codereachable.webservices.restfulwebservices.v2.user.UserV2Secret;
import com.codereachable.webservices.restfulwebservices.v2.user.details.Alias;
import com.codereachable.webservices.restfulwebservices.v2.user.details.Details;
import com.codereachable.webservices.restfulwebservices.v2.utils.repositories.CourseV2Repository;
import com.codereachable.webservices.restfulwebservices.v2.utils.repositories.UserV2Repository;

@Component
public class DbSeeder implements CommandLineRunner {

	@Autowired
	private UserV2Repository userRepository;
	@Autowired
	private CourseV2Repository courseRepository;
	
	@Override
	public void run(String... args) throws Exception {
				
		// Static Courses to be seeded into MongoDB
		CourseV2 javaCourse = new CourseV2("1", "Java", "A simple Java course", 175);
		CourseV2 pythonCourse = new CourseV2("2", "Python", "A simple Python course", 200);
		CourseV2 jsCourse = new CourseV2("3", "JavaScript", "A simple JavaScript course", 150);
		CourseV2 domCourse = new CourseV2("4", "DOM", "A simple DOM course", 100);
		CourseV2 dbsCourse = new CourseV2("5", "Database", "A simple Database course", 125);
		
		// drop all courses
		courseRepository.deleteAll();
		
		// add the static courses to mongoDb (Testing Data)
		List<CourseV2> courses = new ArrayList<>();
		courses.add(javaCourse);
		courses.add(pythonCourse);
		courses.add(jsCourse);
		courses.add(domCourse);
		courses.add(dbsCourse);
		courseRepository.saveAll(courses);

			
		// Static Users to be seeded into MongoDB
		UserV2 adam = new UserV2("1", new Details( new Alias("Adam", "Eden"), new Date(), "adam@yahoo.com", new UserV2Secret("A1b2C3d4")));
		UserV2 eve = new UserV2("2", new Details( new Alias("Eve", "Eden"), new Date(), "eve@facebook.com", new UserV2Secret("1234abcd")));
		UserV2 jack = new UserV2("3", new Details( new Alias("Jack", "Sparro"), new Date(), "jack@live.com",new UserV2Secret("A4C2D1B3")));
		
		// drop all courses
		userRepository.deleteAll();
		
		// add the static users to mongoDb (Testing Data)
		List<UserV2> users = new ArrayList<>();
		users.add(adam);
		users.add(eve);
		users.add(jack);
		userRepository.saveAll(users);
	}

}
