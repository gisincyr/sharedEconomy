package com.sharedEconomy.controllers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sharedEconomy.repositories.AdvertRepository;
import com.sharedEconomy.exceptions.AdvertNotFoundException;
import com.sharedEconomy.models.Advert;

@RestController
@RequestMapping(value="/api", produces="application/json")
public class AdvertController {

	@Autowired
	private AdvertRepository advertRepository;

	@RequestMapping(value="/adverts", method=RequestMethod.GET)
	public List<Advert> getAllAdverts() {
		return advertRepository.findAll();
	}

	@RequestMapping(value="/adverts/{id}", method=RequestMethod.GET)
	public ResponseEntity<Advert> getUsersById(@PathVariable(value = "id") Long advertId) throws AdvertNotFoundException {
		
		Advert advert = advertRepository.findById(advertId)
				.orElseThrow(() -> new AdvertNotFoundException("Advert not found on :: " + advertId));

		return ResponseEntity.ok().body(advert);
	}

	@PostMapping("/adverts")
	public Advert createAdvert(@Valid @RequestBody Advert advert) {
		
		advert.setCreationTimestamp(new Timestamp(new Date().getTime()));
		
		return advertRepository.save(advert);
	}

	@PutMapping("/adverts/{id}")
	public ResponseEntity<Advert> updateAdvert(@PathVariable(value = "id") Long advertId,
			@Valid @RequestBody Advert advertDetails) throws AdvertNotFoundException {
		
		Advert advert = advertRepository.findById(advertId)
				.orElseThrow(() -> new AdvertNotFoundException("Advert not found on :: " + advertId));
		
		/*
		 * user.setEmail(userDetails.getEmail());
		 * user.setLastName(userDetails.getLastName());
		 * user.setFirstName(userDetails.getFirstName()); user.setUpdatedOn(new Date());
		 */
		final Advert updatedAdvert = advertRepository.save(advert);
		return ResponseEntity.ok(updatedAdvert);
	}

	@DeleteMapping("/adverts/{id}")
	public Map<String, Boolean> deleteAdvert(@PathVariable(value = "id") Long advertId) throws AdvertNotFoundException {
		
		Advert advert = advertRepository.findById(advertId)
				.orElseThrow(() -> new AdvertNotFoundException("Advert not found on :: " + advertId));
		
		advertRepository.delete(advert);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
