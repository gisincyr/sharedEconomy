package com.sharedEconomy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sharedEconomy.exceptions.EntityNotFoundException;
import com.sharedEconomy.models.Pricing;
import com.sharedEconomy.repositories.PricingRepository;

@RestController
@RequestMapping(value="/api", produces="application/json")
public class PricingController {

	@Autowired
	private PricingRepository pricingRepository;

	@RequestMapping(value="/pricings", method=RequestMethod.GET)
	public List<Pricing> getAllPrisings() {
		return pricingRepository.findAll();
	}

	@RequestMapping(value="/pricings/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pricing> getPricingById(@PathVariable(value = "id") Long pricingId) throws EntityNotFoundException {
		
		Pricing pricing = pricingRepository.findById(pricingId)
				.orElseThrow(() -> new EntityNotFoundException("Pricing not found on " + pricingId));

		return ResponseEntity.ok().body(pricing);
	}
}