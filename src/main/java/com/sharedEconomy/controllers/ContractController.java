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
import com.sharedEconomy.models.Contract;
import com.sharedEconomy.repositories.ContractRepository;

@RestController
@RequestMapping(value="/api", produces="application/json")
public class ContractController {

	@Autowired
	private ContractRepository contractRepository;
	
	@RequestMapping(value="/contracts", method=RequestMethod.GET)
	public List<Contract> getAllContracts() {
		return contractRepository.findAll();
	}

	@RequestMapping(value="/contracts/{id}", method=RequestMethod.GET)
	public ResponseEntity<Contract> getContractById(@PathVariable(value = "id") Long contractId) throws EntityNotFoundException {
		
		Contract contract = contractRepository.findById(contractId)
				.orElseThrow(() -> new EntityNotFoundException("Contract not found on " + contractId));

		return ResponseEntity.ok().body(contract);
	}

	@PostMapping("/contracts")
	public Contract createContract(@Valid @RequestBody Contract contract) {
		return contractRepository.save(contract);
	}

	@PutMapping("/contracts/{id}")
	public ResponseEntity<Contract> updateContract(@PathVariable(value = "id") Long contractId,
			@Valid @RequestBody Contract contractDetails) throws EntityNotFoundException {
		
		Contract contract = contractRepository.findById(contractId)
				.orElseThrow(() -> new EntityNotFoundException("Contract not found on " + contractId));
		
		contract.setStartDate(contractDetails.getStartDate());
		contract.setEndDate(contractDetails.getEndDate());
		
		final Contract updatedContract = contractRepository.save(contract);
		return ResponseEntity.ok(updatedContract);
	}

	@DeleteMapping("/contracts/{id}")
	public Map<String, Boolean> deleteContract(@PathVariable(value = "id") Long contractId) throws EntityNotFoundException {
		
		Contract contract = contractRepository.findById(contractId)
				.orElseThrow(() -> new EntityNotFoundException("Contract not found on " + contractId));
		
		contractRepository.delete(contract);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
