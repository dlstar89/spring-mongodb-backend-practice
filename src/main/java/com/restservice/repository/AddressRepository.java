package com.restservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.restservice.entity.Address;

@Repository
public interface AddressRepository extends MongoRepository<Address, String>{
	Address findByStreetNameIgnoreCaseLike(String streetName);
}
