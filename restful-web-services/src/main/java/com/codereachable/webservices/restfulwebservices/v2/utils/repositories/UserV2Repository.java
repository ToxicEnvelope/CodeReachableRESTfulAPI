package com.codereachable.webservices.restfulwebservices.v2.utils.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.codereachable.webservices.restfulwebservices.v2.controllers.user.UserV2;

@Repository
public interface UserV2Repository extends MongoRepository<UserV2, String>{
}
