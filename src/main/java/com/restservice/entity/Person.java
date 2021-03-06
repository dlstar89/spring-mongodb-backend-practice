package com.restservice.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "person")
public class Person {

	@Id
	private String id;

	private String name;

	private String familyName;
	
	@DBRef
	private Address address;
	
	@DBRef
	private Set<Phone> phones = new HashSet<Phone>();

	@SuppressWarnings("unused")
	private Person() {}

	public Person(String name, String familyName) {
		this.name = name;
		this.familyName = familyName;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
		//this.address.setPerson(this);
	}

	public Set<Phone> getPhones() {
		return phones;
	}
	
	public void addPhone(Phone phone)
	{
		this.phones.add(phone);
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

	@Override
	public String toString() {
		return String.format(
                "Person[id=%s, name=%s, familyName=%s, address=%s, phone=%s]",
                id, name, familyName, address, phones);
	}
}
