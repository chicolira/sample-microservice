package com.chicolira.clientservice.model;

import lombok.Data;

@Data
public class City {

	private Long id;
	
	private String name;
	
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
