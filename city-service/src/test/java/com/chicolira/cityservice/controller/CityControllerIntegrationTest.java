package com.chicolira.cityservice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.chicolira.cityservice.model.BrazilianStates;
import com.chicolira.cityservice.model.City;
import com.chicolira.cityservice.service.ICityService;
import com.chicolira.cityservice.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
public class CityControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ICityService cityService;
	
	@Test
	public void givenCity_whenSearchCityByName_thenReturnJson() throws Exception {
		
		City recife = new City(1l, "Recife", BrazilianStates.PE);
		given(cityService.findByName("Recife")).willReturn(recife);
		
		mockMvc.perform(get(getApiUrl())
				.param("name", "Recife")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Recife")));
	}
	
	@Test
	public void givenState_whenSearchCityByState_thenReturnJsonArray() throws Exception {
		
		City recife = new City(1l, "Recife", BrazilianStates.PE);
		City caruaru = new City(1l, "Recife", BrazilianStates.PE);
		City garanhuns = new City(1l, "Recife", BrazilianStates.PE);
		
		List<City> pernambucoCities = Arrays.asList(recife, caruaru, garanhuns);

		given(cityService.findByState(BrazilianStates.PE)).willReturn(pernambucoCities);
		
		mockMvc.perform(get(getApiUrl() + "/state")
				.param("state", "PE")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
			    .andExpect(jsonPath("$.cityList", hasSize(3)))
				.andExpect(jsonPath("$.cityList[0].state", is("PE")))
				.andExpect(jsonPath("$.cityList[1].state", is("PE")))
				.andExpect(jsonPath("$.cityList[2].state", is("PE")));
	}
	
	@Test
	public void whenCreateCity_thenReturnCreatedCityAsJson() throws Exception {

		City toCreate = new City("São Paulo", BrazilianStates.SP);
		
		given(cityService.create(toCreate)).willReturn(new City(1l, "São Paulo", BrazilianStates.SP));
		
		mockMvc.perform(post(getApiUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(toCreate)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("São Paulo")))
				.andExpect(jsonPath("$.state", is("SP")));
	}
	
	private String getApiUrl() {
		return "/" + Constants.API_URL_PREFIX + "/cities";
	}
	
	private String asJsonString(Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
