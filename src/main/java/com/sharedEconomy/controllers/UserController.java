package com.sharedEconomy.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sharedEconomy.exceptions.EntityNotFoundException;
import com.sharedEconomy.exceptions.EmailExistsException;
import com.sharedEconomy.repositories.UserRepository;
import com.sharedEconomy.models.AuthenticationBody;
import com.sharedEconomy.models.User;

@RestController
@RequestMapping(value="/api", produces="application/json")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws EntityNotFoundException {
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User not found on " + userId));

		return ResponseEntity.ok().body(user);
	}

	@RequestMapping(value="/users", method=RequestMethod.POST) 
	public User createUser(@Valid @RequestBody User userDetails) throws EmailExistsException {
		
		// Check if email already exists
		if(userRepository.findByEmail(userDetails.getEmail()) != null) 
			throw new EmailExistsException("Email already exists: " + userDetails.getEmail());
		
		// Encrypt password
		userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
		
		return userRepository.save(userDetails);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws EntityNotFoundException {
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User not found on " + userId));
		
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		user.setPassword(passwordEncoder.encode(userDetails.getPassword())); // Encrypt password
		user.setStreet(userDetails.getStreet());
		user.setHousenumber(userDetails.getHousenumber());
		user.setCity(userDetails.getCity());
		
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws EntityNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User not found on " + userId));
		
		userRepository.delete(user);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
		
	/**
	 * Checks the login credentials against the db
	 * @param authBody
	 * @return
	 * @throws BadCredentialsException
	 */
	@RequestMapping(value="/users/login", method=RequestMethod.POST)
	public ResponseEntity<User> checkLogin(@Valid @RequestBody AuthenticationBody authBody) throws EntityNotFoundException {
		User user = userRepository.findByEmail(authBody.getEmail());
		
		if(user == null) {
			throw new BadCredentialsException("No user found with email " + authBody.getEmail());
		}
		
		if(!passwordEncoder.matches(authBody.getPassword(), user.getPassword())) {
			throw new BadCredentialsException("Password or E-Mail wrong");
		}
		
		return ResponseEntity.ok(user);
	}
}
