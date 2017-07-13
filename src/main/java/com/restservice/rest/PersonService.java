package com.restservice.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.restservice.entity.Address;
import com.restservice.entity.Person;
import com.restservice.entity.Phone;
import com.restservice.repository.AddressRepository;
import com.restservice.repository.PersonRepository;
import com.restservice.repository.PhoneRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


//@Service
@RestController
@RequestMapping(value="/api")
@CrossOrigin //makes all services available to ouside applications
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
	
//	private PersonService(){};
	
	@CrossOrigin //enables other apps to make an api call
	@ResponseStatus(code=HttpStatus.OK)
	@RequestMapping(value="/healthCheck", method=RequestMethod.GET)
	@ApiOperation(value="Check health status")
	public void ping() throws InterruptedException{
		Thread.sleep(1000);//Simulate delay of 1 seconds
	}
	
	@RequestMapping(value="/getAllPeople", method=RequestMethod.GET)
	public List<Person> getAllPeople() throws InterruptedException{
		Thread.sleep(1000);//Simulate delay of 1 seconds
		return personRepo.findAll();
	}
	
	@ApiOperation(value = "Get Person By NAME")
	@RequestMapping(value="/getPersonByName", 
					method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public Person getPersonByName(@RequestHeader@ApiParam(value="name to be retrieved",
									required=true,
									defaultValue="Alex")									
									String name){
		return personRepo.findPersonByNameLike(name);
	}
	
	@ResponseStatus(code=HttpStatus.OK)
	@RequestMapping(value="/updatePerson", method=RequestMethod.POST)
	public void updatePerson(@RequestHeader(value="id") String id,
								@RequestHeader(value="name", required=false) String name,
								@RequestHeader(value="familyName", required=false) String familyName
								){
		
		Person p = personRepo.findById(id);
		System.out.println("UPDATING: " + p);
		if(p != null)
		{
			if(name != null && name.length() >= 1)
				p.setName(name);
			
			if(familyName != null && familyName.length() >= 1)
				p.setFamilyName(familyName);
			
			personRepo.save(p);
		}
		
	}
	
	@RequestMapping(value="/getPersonByFamilyName", method=RequestMethod.GET)
	public List<Person> getPersonByFamilyName(@RequestHeader(value="familyName", defaultValue="default") String familyName){
		return personRepo.findByFamilyName(familyName);
	}
	
	@RequestMapping(value="/findByAddress", method=RequestMethod.GET)
	public List<Person> findByAddress(@RequestHeader(value="streetName") String streetName){
		return personRepo.findByAddress(addressRepo.findByStreetNameIgnoreCaseLike(streetName));
	}
	
	@RequestMapping(value="/deleteByID", method=RequestMethod.DELETE)
	public HttpStatus deletePerson(@RequestHeader(value="id") String id) throws InterruptedException{
		personRepo.delete(id);
		
		Thread.sleep(1000);//Simulate delay of 1 seconds
		
		return HttpStatus.OK;
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

	public long total() {
		return personRepo.count();
	}
}
