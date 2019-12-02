package com.chicolira.clientservice.model;

import java.util.List;

import lombok.Data;

@Data
public class CityListResponseWrapper {

	private Long searchTimestamp;
	private List<City> cityList;

	public CityListResponseWrapper() {
		super();
	}
	
	public CityListResponseWrapper(Long searchTimestamp, List<City> cityList) {
		super();
		this.searchTimestamp = searchTimestamp;
		this.cityList = cityList;
	}

}
