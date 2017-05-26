package com.restservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.restservice.entity.Address;
import com.restservice.entity.Person;
import com.restservice.entity.Phone;
import com.restservice.entity.Phone.ePhoneCategory;
import com.restservice.service.PersonService;

@SpringBootApplication
public class RestApplication implements CommandLineRunner {

	@Autowired
	private PersonService myService;

	// Execute code before running the application
	@Override
	public void run(String... arg0) throws Exception {
		
		//myService.createPerson("Bob", "Bobovich", new Address(20, "Bulvar boom"));
		//myService.createPerson("Alex", "Reno", new Address(301, "AAA"));
		
		/*
		myService.deleteAll();
		Person p = new Person("Alex", "Reno");
		myService.addPhone(p, new Phone(ePhoneCategory.home,44345));
		myService.addPhone(p, new Phone(ePhoneCategory.work,5848373));
		myService.createPerson(p, new Address(123,"Detroid"));
		*/
		
		System.out.println("Total people: " + myService.total());
		System.out.println(myService.getAllPeople());
	}

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}
}
