package com.chicolira.cityservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.chicolira.cityservice.model.BrazilianStates;
import com.chicolira.cityservice.model.City;

@RunWith (SpringRunner.class)
@DataJpaTest
public class CityRepositoryIntegrationTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Test
	public void whenFindByName_thenReturnCity() {
		City recife = new City("Recife", BrazilianStates.PE);
		
		entityManager.persist(recife);
		entityManager.flush();
		
		City found = cityRepository.findByName(recife.getName());
		
		assertThat(found.getName())
			.isEqualTo(found.getName());
	}
	
	@Test
	public void whenFindByState_thenReturnThatStateCities() {
		City recife = new City("Recife", BrazilianStates.PE);
		City caruaru = new City("Caruaru", BrazilianStates.PE);
		City garahuns = new City("Garanhuns", BrazilianStates.PE);
		
		entityManager.persist(recife);
		entityManager.persist(caruaru);
		entityManager.persist(garahuns);
		
		entityManager.flush();
		
		List<City> found = cityRepository.findByState(recife.getState());
		
		assertThat(found.size()).isEqualTo(3);
	}
	
	@Test
	public void whenSave_thenReturnSavedCity() {
		
		City recife = new City("Recife", BrazilianStates.PE);
		City saved = cityRepository.save(recife);
		
		assertThat(saved.getName())
		.isEqualTo(recife.getName());
	}
}
