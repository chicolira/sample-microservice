package com.chicolira.clientservice.service;

import com.chicolira.clientservice.exception.ResourceNotFoundException;
import com.chicolira.clientservice.model.Client;

public interface IClientService {

	Client findById(Long id);
	Client findByName(String name);
	Client create(Client client);
	Client update(Long id, Client client) throws ResourceNotFoundException;
	void delete(Long id);
	
}
