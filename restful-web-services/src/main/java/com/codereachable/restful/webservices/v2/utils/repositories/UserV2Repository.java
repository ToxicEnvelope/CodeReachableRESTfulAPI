package com.codereachable.restful.webservices.v2.utils.repositories;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.codereachable.restful.webservices.v2.user.UserV2;


@Repository
public interface UserV2Repository extends MongoRepository<UserV2, String> {
}
