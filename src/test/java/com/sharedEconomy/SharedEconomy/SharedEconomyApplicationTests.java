package com.sharedEconomy.SharedEconomy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sharedEconomy.controllers.AdvertController;
import com.sharedEconomy.controllers.AdvertTypeController;
import com.sharedEconomy.controllers.CityController;
import com.sharedEconomy.controllers.ContractController;
import com.sharedEconomy.controllers.ContractPositionController;
import com.sharedEconomy.controllers.CurrencyController;
import com.sharedEconomy.controllers.PricingController;
import com.sharedEconomy.controllers.PricingTypeController;
import com.sharedEconomy.controllers.UserController;

@SpringBootTest
class SharedEconomyApplicationTests {

	@Autowired
	private AdvertController advertController;
	
	@Autowired
	private AdvertTypeController advertTypeController;
	
	@Autowired
	private CityController cityController;
	
	@Autowired
	private ContractController contractController;
	
	@Autowired
	private ContractPositionController contractPositionController;
	
	@Autowired
	private CurrencyController currencyController;
	
	@Autowired
	private PricingController pricingController;
	
	@Autowired
	private PricingTypeController pricingTypeController;
	
	@Autowired
	private UserController userController;
	
	@Test
	void contextLoads() {
		assertThat(advertController).isNotNull();
		assertThat(advertTypeController).isNotNull();
		assertThat(cityController).isNotNull();
		assertThat(contractController).isNotNull();
		assertThat(contractPositionController).isNotNull();
		assertThat(currencyController).isNotNull();
		assertThat(pricingController).isNotNull();
		assertThat(pricingTypeController).isNotNull();
		assertThat(userController).isNotNull();
	}
}
