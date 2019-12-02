package com.chicolira.clientservice.model;

import lombok.Getter;

public enum Gender {

	M("Male"),
	F("Female"),
	U("Uninformed");
	
	@Getter
	private String description;

	private Gender(String description) {
		this.description = description;
	}
	
}
