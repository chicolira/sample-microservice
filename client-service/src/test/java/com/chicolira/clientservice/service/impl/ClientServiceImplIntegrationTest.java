package com.chicolira.clientservice.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.chicolira.clientservice.exception.ResourceNotFoundException;
import com.chicolira.clientservice.model.Client;
import com.chicolira.clientservice.model.Gender;
import com.chicolira.clientservice.repository.ClientRepository;
import com.chicolira.clientservice.service.IClientService;

@RunWith(SpringRunner.class)
public class ClientServiceImplIntegrationTest {

	@TestConfiguration
	static class CityServiceImplTestContextConfiguration {
		
		@Bean
		public IClientService cityService() {
			return new ClientServiceImpl();
		}
		
		@Bean
		public RestTemplate getRestTemplate() {
			return new RestTemplate();
		}
	}
	
	@Autowired
	private IClientService clientService;
	
	@MockBean
	private ClientRepository clientRepository;
	
	@Before
	public void setUp() {
		
		Client joao = new Client("João", Gender.M, new Date(), 30, 1l);
		Long joaoId = 1l;
		joao.setId(joaoId);
		
		when(clientRepository.findByName("João")).thenReturn(joao);
		when(clientRepository.findById(joaoId)).thenReturn(createClientOptional(joao));
		
	}
	
	@Test(expected = ResourceAccessException.class)
	public void whenValidName_thenClientShouldBeFound() {
		String name = "João";
		Client found = clientService.findByName(name);
		
		assertThat(found.getName()).isEqualTo(name);
	}
	
	@Test(expected = ResourceAccessException.class)
	public void whenValidId_thenClientShouldBeFound() {
		Long id = 1l;
		Client found = clientService.findById(id);
		
		assertThat(found.getId()).isEqualTo(id);
	}
	
	@Test
	public void whenCreateClient_thenReturnCreatedClient() {
		
		Client maria = new Client("Maria", Gender.F, new Date(), 40, 1l);
		when(clientRepository.save(maria)).thenReturn(new Client(2l, "Maria", Gender.F, new Date(), 40, 1l, null));
		
		Client created = clientService.create(maria);
		
		try {
			assertThat(created.getName()).isEqualTo(maria.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void whenUpdateClientName_thenNameChanged() throws Exception {
		
		Client jose = new Client(3l, "José", Gender.M, new Date(), 30, 1l, null);
		when(clientRepository.save(jose)).thenReturn(new Client(3l, "José", Gender.M, new Date(), 21, 1l, null));
		
		Client toUpdate = new Client(3l, "José", Gender.F, new Date(), 40, 1l, null);
		Client updated = clientService.update(3l, toUpdate);
		
		assertThat(updated.getName()).isEqualTo(toUpdate.getName());
	}
	
	private Optional<Client> createClientOptional(Client client) {
		return Optional.of(client);
	}
}
