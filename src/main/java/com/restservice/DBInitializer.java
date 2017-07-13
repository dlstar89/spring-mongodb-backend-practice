package com.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import com.restservice.entity.Address;
import com.restservice.entity.Person;
import com.restservice.entity.Phone;
import com.restservice.entity.Phone.ePhoneCategory;
import com.restservice.rest.PersonService;
import com.restservice.security.SecUserDetailsService;
import com.restservice.security.User;

@Component
public class DBInitializer implements CommandLineRunner {

	@Autowired
	private PersonService myService;
	
	@Autowired
	private SecUserDetailsService secService;
	
	@Override
	public void run(String... args) throws Exception {
		
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
		
		Person p3 = new Person("Henori", "Zekto");
		myService.addPhone(p3, new Phone(ePhoneCategory.home,9957664));
		myService.createPerson(p3, new Address(23,"Luna Monoto"));
		
		Person p4 = new Person("Kenzi", "Yak");
		//myService.addPhone(p3, new Phone(ePhoneCategory.home,9957664));
		myService.createPerson(p4, new Address(999,"XXX"));
		
		Person p5 = new Person("Qazs", "Aswer");
		myService.createPerson(p5, new Address(12,"Qwerty"));
				
		System.out.println("Total people: " + myService.total());
		//System.out.println(myService.getAllPeople());
		
		
		
		/*CREATE USERS FOR AUTHORIZATION*/
		secService.deleteAll();
		secService.CreateUser(new User("admin","admin",new String[]{"ADMIN","USER"}));
		secService.CreateUser(new User("user","user",new String[]{"USER"}));
		
	}
}
