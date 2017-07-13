package com.restservice.security;

import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	User findByUsername(String username);
	User findById(String id);
}
