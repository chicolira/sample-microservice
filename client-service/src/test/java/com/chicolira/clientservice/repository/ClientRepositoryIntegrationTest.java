package com.chicolira.clientservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.chicolira.clientservice.model.Client;
import com.chicolira.clientservice.model.Gender;


@RunWith (SpringRunner.class)
@DataJpaTest
public class ClientRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Test
	public void whenFindByName_thenReturnClient() {
		
		Client jose = new Client("José", Gender.M, new Date(), 22, 1l);
		
		entityManager.persist(jose);
		entityManager.flush();
		
		Client found = clientRepository.findByName(jose.getName());
		
		assertThat(found.getName())
			.isEqualTo(found.getName());
	}
	
	@Test
	public void whenSave_thenReturnSavedClient() {
		
		Client joao = new Client("João", Gender.M, new Date(), 30, 1l);
		Client saved = clientRepository.save(joao);
		
		assertThat(saved.getName())
		.isEqualTo(joao.getName());
	}
	
	@Test
	public void whenUpdate_thenNameChanged() {
		
		Client joao = new Client("João", Gender.M, new Date(), 30, 1l);
		
		Client saved = entityManager.persist(joao);
		entityManager.flush();
		
		saved.setName("Daniel");
		Client daniel = clientRepository.save(saved);
		
		assertThat(daniel.getName())
		.isEqualTo(saved.getName());
	}
	
	@Test
	public void whenUpdate_thenIdIsUnChanged() {
		
		Client joao = new Client("João", Gender.M, new Date(), 30, 1l);
		
		Client saved = entityManager.persist(joao);
		entityManager.flush();
		
		saved.setName("Daniel");
		Client daniel = clientRepository.save(saved);
		
		assertThat(daniel.getId())
		.isEqualTo(saved.getId());
	}
	
	@Test
	public void whenFindById_thenResourceIsFound() {
		Client joao = new Client("João", Gender.M, new Date(), 30, 1l);
		
		Client saved = entityManager.persist(joao);
		entityManager.flush();
		
		Long id = saved.getId();
		
		Client found = clientRepository.findById(id).orElse(null);
		
		assertThat(found.getId()).isEqualTo(id);
	}
	
	@Test
	public void whenDelete_thenResourceShouldNotExist() {
		
		Client joao = new Client("João", Gender.M, new Date(), 30, 1l);
		
		Client saved = entityManager.persist(joao);
		entityManager.flush();
		
		Long id = saved.getId();
		clientRepository.deleteById(id);
		
		Client found = clientRepository.findById(id).orElse(null);
		
		assertNull(found);
	}
}
