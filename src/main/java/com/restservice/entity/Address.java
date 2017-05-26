package com.restservice.entity;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Document(collection = "address")
public class Address {
	
	@Id
	private String id;

	private Integer streetNumber;

	private String streetName;
	
	//@DBRef
	//private Person person;
	
	protected Address() {}

	public Address(Integer streetNumber, String streetName) {
		this.streetNumber = streetNumber;
		this.streetName = streetName;
	}
	
	
	
	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/*
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	*/

	@Override
	public String toString() {
		return String.format(
                "Address[id=%s, streetNumber=%s, streetName=%s]",
                id, streetNumber, streetName);
	}
}
