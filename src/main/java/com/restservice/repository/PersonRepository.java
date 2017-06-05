package com.restservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.Repository;

import com.restservice.entity.Address;
import com.restservice.entity.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String>{
	Person findById(String id);
	
	List<Person> findByNameLike(String name);
	List<Person> findByFamilyName(String familyName);
	//Person findPersonByName(String name);
	
	List<Person> findByAddress(Address address);
}
