package com.restservice.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restservice.entity.Address;
import com.restservice.entity.Person;
import com.restservice.entity.Phone;
import com.restservice.repository.AddressRepository;
import com.restservice.repository.PersonRepository;
import com.restservice.repository.PhoneRepository;


//@Service
@RestController
@RequestMapping(value="/api")
public class PersonService {

	private PersonRepository personRepo;
	private AddressRepository addressRepo;
	private PhoneRepository phoneRepo;

	@Autowired
	public PersonService(PersonRepository personRepo,
						 AddressRepository addressRepo,
						 PhoneRepository phoneRepo) {
		this.personRepo = personRepo;
		this.addressRepo = addressRepo;
		this.phoneRepo = phoneRepo;
	}
	
	@CrossOrigin //enables other apps to make an api call
	@RequestMapping(value="/getAllPeople")
	public Iterable<Person> getAllPeople(){
		return personRepo.findAll();
	}
	
	@RequestMapping(value="/getPersonByName")
	public Iterable<Person> getPersonByName(@RequestHeader(value="name") String name){
		return personRepo.findByNameLike(name);
	}
	
	@RequestMapping(value="/getPersonByFamilyName")
	public Iterable<Person> getPersonByFamilyName(@RequestHeader(value="familyName", defaultValue="default") String familyName){
		return personRepo.findByFamilyName(familyName);
	}
	
	@RequestMapping(value="/findByAddress")
	public Iterable<Person> findByAddress(@RequestHeader(value="streetName") String streetName){
		return personRepo.findByAddress(addressRepo.findByStreetNameIgnoreCaseLike(streetName));
	}
	
	public void deleteAll()
	{
		personRepo.deleteAll();
		addressRepo.deleteAll();
		phoneRepo.deleteAll();
	}
	
	public Person createPerson(Person person) {		
		return personRepo.insert(person);
	}
	
	public Person createPerson(Person person, Address address) {
		person.setAddress(address);
		
		addressRepo.insert(address);
		
		return personRepo.insert(person);
	}
	
	public Phone addPhone(Person person, Phone phone)
	{
		person.addPhone(phone);
		
		return phoneRepo.insert(phone);
	}

	/*
	public Iterable<Person> lookup() {
		return myRepo.findAll();
	}
	*/

	public long total() {
		return personRepo.count();
	}
}
