package com.chicolira.clientservice.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.chicolira.clientservice.exception.ResourceNotFoundException;
import com.chicolira.clientservice.model.City;
import com.chicolira.clientservice.model.Client;
import com.chicolira.clientservice.repository.ClientRepository;
import com.chicolira.clientservice.service.IClientService;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private RestTemplate restTemplate; 
	
	@Override
	public Client findById(Long id) {
		Client client = clientRepository.findById(id).orElse(null);
		fetchUserCity(client);
		return client;
	}

	@Override
	public Client findByName(String name) {
		Client client = clientRepository.findByName(name);
		fetchUserCity(client);
		return client;
	}

	@Override
	public Client create(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public Client update(Long id, Client client) throws ResourceNotFoundException {
		
		Client clientBeforeUpdate = findById(id);
		
		if (clientBeforeUpdate == null) {
			throw new ResourceNotFoundException("Resource [Client] not Found");
		}
		
		BeanUtils.copyProperties(client, clientBeforeUpdate);
		
		return clientRepository.save(clientBeforeUpdate);
	}

	@Override
	public void delete(Long id) {
		clientRepository.deleteById(id);
	}

	private void fetchUserCity(Client client) {
		if (client != null) {
			City city = restTemplate.getForObject("http://city-service/api/cities/" + client.getCityId(), City.class);
			client.setCity(city);
		}
	}
}
