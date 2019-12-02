package com.chicolira.clientservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private Date birthDate;
	
	private Integer age;
	
	private Long cityId;
	
	@Transient
	private City city;
	
	public Client() {
		super();
	}

	public Client(String name, Gender gender, Date birthDate, Integer age, Long cityId) {
		super();
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.age = age;
		this.cityId = cityId;
	}
	
	public Client(String name, Gender gender, Date birthDate, Integer age, Long cityId, City city) {
		super();
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.age = age;
		this.cityId = cityId;
		this.city = city;
	}
	
	public Client(Long id, String name, Gender gender, Date birthDate, Integer age, Long cityId, City city) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.age = age;
		this.cityId = cityId;
		this.city = city;
	}

}
