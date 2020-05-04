package com.sharedEconomy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sharedEconomy.exceptions.EntityNotFoundException;
import com.sharedEconomy.models.City;
import com.sharedEconomy.repositories.CityRepository;

@RestController
@RequestMapping(value="/api", produces="application/json")
public class CityController {

	@Autowired
	private CityRepository cityRepository;

	@RequestMapping(value="/cities", method=RequestMethod.GET)
	public List<City> getAllCities() {
		return cityRepository.findAll();
	}

	@RequestMapping(value="/cities/{id}", method=RequestMethod.GET)
	public ResponseEntity<City> getCityById(@PathVariable(value = "id") Long cityId) throws EntityNotFoundException {
		
		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> new EntityNotFoundException("City not found on " + cityId));

		return ResponseEntity.ok().body(city);
	}
}