package com.chicolira.cityservice;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.chicolira.cityservice.controller.CityControllerIntegrationTest;
import com.chicolira.cityservice.repository.CityRepositoryIntegrationTest;
import com.chicolira.cityservice.service.CityServiceImplIntegrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CityRepositoryIntegrationTest.class,
	CityServiceImplIntegrationTest.class,
	CityControllerIntegrationTest.class
})
public class CityServiceAppTest {

}
