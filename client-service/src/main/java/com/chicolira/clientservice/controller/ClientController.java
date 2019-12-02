package com.chicolira.clientservice.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chicolira.clientservice.exception.ResourceNotFoundException;
import com.chicolira.clientservice.exception.ResourceNotFoundRuntimeException;
import com.chicolira.clientservice.model.Client;
import com.chicolira.clientservice.service.IClientService;
import com.chicolira.clientservice.util.Constants;

@RestController
@RequestMapping(value = Constants.API_URL_PREFIX + "/clients")
public class ClientController {

	@Autowired
	private IClientService clientService;
	
	@GetMapping
	public Client findByName(@RequestParam(name = "name") String name) {
		return clientService.findByName(name);
	}
	
	@GetMapping(value = "/{id}")
	public Client findById(@PathVariable(name = "id") Long id) {
		return clientService.findById(id);
	}
	
	@PostMapping
	public Client create(@RequestBody Client client, HttpServletResponse response) {

		Client created = clientService.create(client);
		
		if (created != null) {
			response.setStatus(HttpStatus.CREATED.value());
		}
		
		return created;
	}
	
	@PutMapping(value = "/{id}")
	public Client update(@PathVariable Long id, @RequestBody Client client) {
		
		try {
			return clientService.update(id, client);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundRuntimeException(e.getMessage());
		}
	}
	
	@DeleteMapping
	public void delete(Long id) {
		clientService.delete(id);
	}
}
