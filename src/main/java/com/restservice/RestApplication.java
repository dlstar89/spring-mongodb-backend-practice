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
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

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
		
		myService.deleteAll();
		
		Person p1 = new Person("Alex", "Reno");
		myService.addPhone(p1, new Phone(ePhoneCategory.home,44345));
		myService.addPhone(p1, new Phone(ePhoneCategory.work,5848373));
		myService.createPerson(p1, new Address(123,"Detroid"));
		
		Person p2 = new Person("Zeq", "Kel");
		myService.addPhone(p2, new Phone(ePhoneCategory.work,444444));
		myService.addPhone(p2, new Phone(ePhoneCategory.home,332978));
		myService.addPhone(p2, new Phone(ePhoneCategory.work,24242));
		myService.createPerson(p2, new Address(654323,"Detrax"));
		
		System.out.println("Total people: " + myService.total());
		//System.out.println(myService.getAllPeople());
	}

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}
}
