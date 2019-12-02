package com.chicolira.cityservice.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private BrazilianStates state;
	
	public City() {
	}

	public City(String name, BrazilianStates state) {
		this.name = name;
		this.state = state;
	}
	
	public City(Long id, String name, BrazilianStates state) {
		this.id = id;
		this.name = name;
		this.state = state;
	}
}
