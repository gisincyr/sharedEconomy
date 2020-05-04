package com.sharedEconomy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sharedEconomy.exceptions.EntityNotFoundException;
import com.sharedEconomy.models.AdvertType;
import com.sharedEconomy.repositories.AdvertTypeRepository;

@RestController
@RequestMapping(value="/api", produces="application/json")
public class AdvertTypeController {

	@Autowired
	private AdvertTypeRepository advertTypeRepository;

	@RequestMapping(value="/adverttypes", method=RequestMethod.GET)
	public List<AdvertType> getAllAdvertTypes() {
		return advertTypeRepository.findAll();
	}

	@RequestMapping(value="/adverttypes/{id}", method=RequestMethod.GET)
	public ResponseEntity<AdvertType> getAdvertTypeById(@PathVariable(value = "id") Long advertTypeId) throws EntityNotFoundException {
		
		AdvertType advertType = advertTypeRepository.findById(advertTypeId)
				.orElseThrow(() -> new EntityNotFoundException("AdvertType not found on " + advertTypeId));

		return ResponseEntity.ok().body(advertType);
	}
}
