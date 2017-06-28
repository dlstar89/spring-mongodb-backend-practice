package com.restservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.restservice.entity.Phone;

@Repository
public interface PhoneRepository extends MongoRepository<Phone, String>{
	//List<Person> findByName(@Param("name") String name);
	//List<Person> findByFamilyName(@Param("familyName") String familyName);
	//Person findPersonByName(String name);
}
