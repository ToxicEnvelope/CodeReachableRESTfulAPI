package com.codereachable.restful.webservices.v2.utils.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codereachable.restful.webservices.v2.content.CourseV2;
import com.codereachable.restful.webservices.v2.user.UserV2;
import com.codereachable.restful.webservices.v2.user.UserV2Secret;
import com.codereachable.restful.webservices.v2.user.details.Address;
import com.codereachable.restful.webservices.v2.user.details.Alias;
import com.codereachable.restful.webservices.v2.user.details.Details;
import com.codereachable.restful.webservices.v2.utils.repositories.CourseV2Repository;
import com.codereachable.restful.webservices.v2.utils.repositories.UserV2Repository;

@Component
public class DbSeeder implements CommandLineRunner {

	@Autowired
	private UserV2Repository userRepository;
	@Autowired
	private CourseV2Repository courseRepository;
	
	@Override
	public void run(String... args) throws Exception {
				
		// Static Courses to be seeded into MongoDB
		CourseV2 javaCourse = new CourseV2("Java", "A simple Java course", 175);
		CourseV2 pythonCourse = new CourseV2("Python", "A simple Python course", 200);
		CourseV2 jsCourse = new CourseV2("JavaScript", "A simple JavaScript course", 150);
		CourseV2 domCourse = new CourseV2("DOM", "A simple DOM course", 100);
		CourseV2 dbsCourse = new CourseV2("Database", "A simple Database course", 125);
		
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
		UserV2 adam = new UserV2(new Details( new Alias("Adam", "Eden"), new Address("IL", "IL", 972,  "Nahariya", "King Solomon St. Building 8", 22202), new Date(), "adam@yahoo.com", new UserV2Secret("A1b2C3d4")));
		UserV2 eve = new UserV2(new Details( new Alias("Eve", "Eden"), new Address("US", "MA", 1,  "Somewhere", "Main St 2212/6", 654321), new Date(), "eve@facebook.com", new UserV2Secret("1234abcd")));
		UserV2 jack = new UserV2(new Details( new Alias("Jack", "Sparro"), new Address("RU", "SP", 970,  "San Petersburg", "Romanov Blv. 131", 4567892), new Date(), "jack@live.com", new UserV2Secret("A4C2D1B3")));
		
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
