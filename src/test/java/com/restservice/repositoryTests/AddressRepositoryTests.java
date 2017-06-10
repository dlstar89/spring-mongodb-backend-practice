package com.restservice.repositoryTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.restservice.entity.Address;
import com.restservice.repository.AddressRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressRepositoryTests {
	@Autowired AddressRepository addressRepo;		
	
	@Test
	public void testPersonRepo() {
		//initializeRepos();
		addressRepo.deleteAll();
		
		Address adr = new Address(333, "Bulvar");
		addressRepo.save(adr);
		
		assertFalse("Wrong ammount of addresses in the repo", addressRepo.count() > 1);
	}
}
