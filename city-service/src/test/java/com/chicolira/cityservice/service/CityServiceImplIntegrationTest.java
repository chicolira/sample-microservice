package com.chicolira.cityservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.chicolira.cityservice.model.BrazilianStates;
import com.chicolira.cityservice.model.City;
import com.chicolira.cityservice.repository.CityRepository;
import com.chicolira.cityservice.service.impl.CityServiceImpl;

@RunWith(SpringRunner.class)
public class CityServiceImplIntegrationTest {

	@TestConfiguration
	static class CityServiceImplTestContextConfiguration {
		
		@Bean
		public ICityService cityService() {
			return new CityServiceImpl();
		}
	}
	
	@Autowired
	private ICityService cityService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@Before
	public void setUp() {
		City recife = new City(1l, "Recife", BrazilianStates.PE);
		City caruaru = new City(2l, "Caruaru", BrazilianStates.PE);
		City garanhuns = new City(1l, "Garanhuns", BrazilianStates.PE);
		
		List<City> pernambucoCities = Arrays.asList(
				recife, caruaru, garanhuns
		); 
		
		when(cityRepository.findByName(recife.getName()))
			.thenReturn(recife);
		
		when(cityRepository.findByState(BrazilianStates.PE))
			.thenReturn(pernambucoCities);
		
		City saoPaulo = new City(null, "São Paulo", BrazilianStates.SP);
		
		when(cityRepository.save(saoPaulo))
			.thenReturn(new City(1l, "São Paulo", BrazilianStates.SP));
	}
	
	@Test
	public void whenValidName_thenCityShouldBeFound() {
		String name = "Recife";
		City found = cityService.findByName(name);
		
		assertThat(found.getName()).isEqualTo(name);
	}
	
	@Test
	public void whenValidState_thenThatStateCitiesShouldBeFound() {
		BrazilianStates state = BrazilianStates.PE;
		List<City> foundCities = cityService.findByState(state);

		assertThat(foundCities.size()).isEqualTo(3);
	}
	
	@Test
	public void whenCreateCity_thenReturnCreatedCity() {
		
		City saoPaulo = new City(null, "São Paulo", BrazilianStates.SP);
		City created = cityService.create(saoPaulo);
		
		assertThat(created.getName()).isEqualTo(saoPaulo.getName());
	}
}
