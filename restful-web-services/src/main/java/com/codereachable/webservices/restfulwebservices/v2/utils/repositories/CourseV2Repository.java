package com.codereachable.webservices.restfulwebservices.v2.utils.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.codereachable.webservices.restfulwebservices.v2.content.CourseV2;

@Repository
public interface CourseV2Repository extends MongoRepository<CourseV2, String> {
}
