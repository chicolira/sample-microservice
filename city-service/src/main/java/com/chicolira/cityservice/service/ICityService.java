package com.chicolira.cityservice.service;

import java.util.List;

import com.chicolira.cityservice.model.BrazilianStates;
import com.chicolira.cityservice.model.City;

public interface ICityService {

	List<City> findByState(BrazilianStates state);
	City findByName(String name);
	City findById(Long id);
	City create(City city);
}
