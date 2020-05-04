package com.sharedEconomy.controllers;

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

import com.sharedEconomy.exceptions.EntityNotFoundException;
import com.sharedEconomy.models.ContractPosition;
import com.sharedEconomy.repositories.ContractPositionRepository;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class ContractPositionController {

	@Autowired
	private ContractPositionRepository contractPositionRepository;

	@RequestMapping(value = "/contractpositions", method = RequestMethod.GET)
	public List<ContractPosition> getAllContractPositions() {
		return contractPositionRepository.findAll();
	}

	@RequestMapping(value = "/contractpositions/{id}", method = RequestMethod.GET)
	public ResponseEntity<ContractPosition> getContractPositionById(@PathVariable(value = "id") Long contractPositionId)
			throws EntityNotFoundException {

		ContractPosition contractPosition = contractPositionRepository.findById(contractPositionId).orElseThrow(
				() -> new EntityNotFoundException("ContractPosition not found on " + contractPositionId));

		return ResponseEntity.ok().body(contractPosition);
	}

	@PostMapping("/contractpositions")
	public ContractPosition createContract(@Valid @RequestBody ContractPosition contractPosition) {
		return contractPositionRepository.save(contractPosition);
	}

	@PutMapping("/contractpositions/{id}")
	public ResponseEntity<ContractPosition> updateContract(@PathVariable(value = "id") Long contractPositionId,
			@Valid @RequestBody ContractPosition contractPositionDetails) throws EntityNotFoundException {
		
		ContractPosition contractPosition = contractPositionRepository.findById(contractPositionId).orElseThrow(
				() -> new EntityNotFoundException("ContractPosition not found on " + contractPositionId));
		
		contractPosition.setRating(contractPositionDetails.getRating());
		
		final ContractPosition updatedContractPosition = contractPositionRepository.save(contractPosition);
		return ResponseEntity.ok(updatedContractPosition);
	}

	@DeleteMapping("/contractpositions/{id}")
	public Map<String, Boolean> deleteContractPosition(@PathVariable(value = "id") Long contractPositionId)
			throws EntityNotFoundException {
		
		ContractPosition contractPosition = contractPositionRepository.findById(contractPositionId).orElseThrow(
				() -> new EntityNotFoundException("ContractPosition not found on " + contractPositionId));
		
		contractPositionRepository.delete(contractPosition);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
