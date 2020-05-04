package com.sharedEconomy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sharedEconomy.exceptions.EntityNotFoundException;
import com.sharedEconomy.models.Currency;
import com.sharedEconomy.repositories.CurrencyRepository;

@RestController
@RequestMapping(value="/api", produces="application/json")
public class CurrencyController {

	@Autowired
	private CurrencyRepository currencyRepository;

	@RequestMapping(value="/currencies", method=RequestMethod.GET)
	public List<Currency> getAllCurrencies() {
		return currencyRepository.findAll();
	}

	@RequestMapping(value="/currencies/{id}", method=RequestMethod.GET)
	public ResponseEntity<Currency> getCurrencyById(@PathVariable(value = "id") Long currencyId) throws EntityNotFoundException {
		
		Currency currency = currencyRepository.findById(currencyId)
				.orElseThrow(() -> new EntityNotFoundException("Currency not found on " + currencyId));

		return ResponseEntity.ok().body(currency);
	}
}