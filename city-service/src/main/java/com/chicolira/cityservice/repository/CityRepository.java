package com.chicolira.cityservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chicolira.cityservice.model.BrazilianStates;
import com.chicolira.cityservice.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	public City findByName(String name);
	public List<City> findByState(BrazilianStates state);
}
