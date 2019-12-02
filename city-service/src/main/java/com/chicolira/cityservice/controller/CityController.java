package com.chicolira.cityservice.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chicolira.cityservice.model.BrazilianStates;
import com.chicolira.cityservice.model.City;
import com.chicolira.cityservice.model.CityListResponseWrapper;
import com.chicolira.cityservice.service.ICityService;
import com.chicolira.cityservice.util.Constants;

@RestController
@RequestMapping(value = Constants.API_URL_PREFIX + "/cities")
public class CityController {

	private ICityService cityService;
	
	@Autowired
	public CityController(ICityService cityService) {
		this.cityService = cityService;
	}
	
	@GetMapping(value = "/state")
	public CityListResponseWrapper findByState(@RequestParam(name = "state") BrazilianStates state) {
		List<City> foundCities = cityService.findByState(state);
		Long timeStamp = new Date().getTime();
		
		return new CityListResponseWrapper(timeStamp, foundCities);
	}
	
	@GetMapping
	public City findByName(@RequestParam(name = "name") String name) {
		return cityService.findByName(name);
	}
	
	@GetMapping(value = "/{id}")
	public City findById(@PathVariable(name = "id") Long id) {
		return cityService.findById(id);
	}
	
	@PostMapping
	public City create(@RequestBody City city, HttpServletResponse response) {
		
		City createdCity = cityService.create(city);
		
		if (createdCity != null && createdCity.getId() != null) {
			response.setStatus(HttpStatus.CREATED.value());
		}
		
		return createdCity;
	}
}
