package com.sharedEconomy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sharedEconomy.exceptions.EntityNotFoundException;
import com.sharedEconomy.models.PricingType;
import com.sharedEconomy.repositories.PricingTypeRepository;

@RestController
@RequestMapping(value="/api", produces="application/json")
public class PricingTypeController {

	@Autowired
	private PricingTypeRepository pricingTypeRepository;

	@RequestMapping(value="/pricingtypes", method=RequestMethod.GET)
	public List<PricingType> getAllPricingTypes() {
		return pricingTypeRepository.findAll();
	}

	@RequestMapping(value="/pricingtypes/{id}", method=RequestMethod.GET)
	public ResponseEntity<PricingType> getPricingTypeById(@PathVariable(value = "id") Long pricingTypeId) throws EntityNotFoundException {
		
		PricingType pricing = pricingTypeRepository.findById(pricingTypeId)
				.orElseThrow(() -> new EntityNotFoundException("Pricingtype not found on " + pricingTypeId));

		return ResponseEntity.ok().body(pricing);
	}
}