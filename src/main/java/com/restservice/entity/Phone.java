package com.restservice.entity;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.*;


@Document(collection = "phone")
public class Phone {
	public static enum ePhoneCategory{
		home,
		work
	}
	
	@Id
	private String id;
	
	private ePhoneCategory phoneCategory;
	
	private Integer number;

	@SuppressWarnings("unused")
	private Phone(){};
	
	public Phone(ePhoneCategory category, Integer number)
	{
		this.phoneCategory = category;
		this.number = number;
	}
	
	
	public ePhoneCategory getPhoneCategory() {
		return phoneCategory;
	}

	public void setPhoneCategory(ePhoneCategory phoneCategory) {
		this.phoneCategory = phoneCategory;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return String.format(
                "Phone[id=%s, phoneCategory=%s, number=%s]",
                id, phoneCategory, number);
	}
}
