package com.sharedEconomy.SharedEconomy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldGetAdverts() throws Exception {
		mockMvc.perform(get("/api/adverts")).andDo(print())
	     .andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetAdvertTypes() throws Exception {
		mockMvc.perform(get("/api/adverttypes")).andDo(print())
	     .andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetCities() throws Exception {
		mockMvc.perform(get("/api/cities")).andDo(print())
	     .andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetContracts() throws Exception {
		mockMvc.perform(get("/api/contracts")).andDo(print())
	     .andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetContractPositions() throws Exception {
		mockMvc.perform(get("/api/contractpositions")).andDo(print())
	     .andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetCurrencies() throws Exception {
		mockMvc.perform(get("/api/currencies")).andDo(print())
	     .andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetPricings() throws Exception {
		mockMvc.perform(get("/api/pricings")).andDo(print())
	     .andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetPricingTypes() throws Exception {
		mockMvc.perform(get("/api/pricingtypes")).andDo(print())
	     .andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetUsers() throws Exception {
		mockMvc.perform(get("/api/users")).andDo(print())
	     .andExpect(status().isOk());
	}
}