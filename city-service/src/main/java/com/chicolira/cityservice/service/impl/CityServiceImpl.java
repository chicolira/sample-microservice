package com.chicolira.cityservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chicolira.cityservice.model.BrazilianStates;
import com.chicolira.cityservice.model.City;
import com.chicolira.cityservice.repository.CityRepository;
import com.chicolira.cityservice.service.ICityService;

@Service
public class CityServiceImpl implements ICityService {
	
	@Autowired
	private CityRepository cityRepository;

	@Override
	public List<City> findByState(BrazilianStates state) {
		return cityRepository.findByState(state);
	}

	@Override
	public City findByName(String name) {
		return cityRepository.findByName(name);
	}
	
	@Override
	public City findById(Long id) {
		return cityRepository.findById(id).orElse(null);
	}

	@Override
	public City create(City city) {
		return cityRepository.save(city);
	}

}
