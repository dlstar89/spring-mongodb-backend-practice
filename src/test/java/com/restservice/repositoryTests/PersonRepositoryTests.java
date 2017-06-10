package com.restservice.repositoryTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.restservice.entity.Person;
import com.restservice.repository.PersonRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTests {
	@Autowired PersonRepository personRepo;
	
	@Test
	public void testPersonRepo() {
		//initializeRepos();
		personRepo.deleteAll();
		
		Person p = new Person("Bob", "Serto");
		personRepo.save(p);
		
		assertFalse("Wrong ammount of people in the repo", personRepo.count() > 1);
		
		
		personRepo.deleteAll();
		assertFalse("Person repo is nor empty", personRepo.count() > 0);
	}
}
