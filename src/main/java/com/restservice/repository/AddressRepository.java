package com.restservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.restservice.entity.Address;
import com.restservice.entity.Person;

@Repository
public interface AddressRepository extends MongoRepository<Address, String>{
	//List<Person> findByName(@Param("name") String name);
	//List<Person> findByFamilyName(@Param("familyName") String familyName);
	//Person findPersonByName(String name);
}
