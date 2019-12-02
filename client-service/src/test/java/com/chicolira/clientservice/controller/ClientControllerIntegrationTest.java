package com.chicolira.clientservice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.chicolira.clientservice.model.Client;
import com.chicolira.clientservice.model.Gender;
import com.chicolira.clientservice.service.IClientService;
import com.chicolira.clientservice.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IClientService clientService;
	
	@Test
	public void givenClient_whenSearchClientByName_thenReturnJson() throws Exception {
		
		Client joao = new Client(1l, "João", Gender.M, new Date(), 30, 1l, null);
		
		given(clientService.findByName("João")).willReturn(joao);
		
		mockMvc.perform(get(getApiUrl())
				.param("name", "João")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("João")));
	}
	
	@Test
	public void givenClient_whenSearchClientById_thenReturnJson() throws Exception {
		
		Client joao = new Client(1l, "João", Gender.M, new Date(), 30, 1l, null);
		
		given(clientService.findById(1l)).willReturn(joao);
		
		mockMvc.perform(get(getApiUrl() + "/1")
				.param("name", "João")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)));
	}
	
	@Test
	public void whenCreateClient_thenReturnCreatedClientAsJson() throws Exception {

		Client toCreate = new Client("João", Gender.M, new Date(), 30, 1l);
		given(clientService.create(toCreate)).willReturn(new Client(1l, "João", Gender.M, new Date(), 30, 1l, null));
		
		mockMvc.perform(post(getApiUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(toCreate)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("João")))
				.andExpect(jsonPath("$.gender", is("M")));
	}
	
	@Test
	public void whenUpdateClient_thenReturnUpdatedClientAsJson() throws Exception {

		Client toCreate = new Client(1l, "Rafael", Gender.M, new Date(), 30, 1l, null);
		given(clientService.update(1l, toCreate)).willReturn(new Client(1l, "Raphael", Gender.M, new Date(), 30, 1l, null));
		
		mockMvc.perform(put(getApiUrl() + "/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(toCreate)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Raphael")))
				.andExpect(jsonPath("$.gender", is("M")));
	}
	
	private String getApiUrl() {
		return "/" + Constants.API_URL_PREFIX + "/clients";
	}
	
	private String asJsonString(Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
