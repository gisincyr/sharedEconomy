package com.sharedEconomy.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sharedEconomy.exceptions.UserNotFoundException;
import com.sharedEconomy.exceptions.EmailExistsException;
import com.sharedEconomy.repositories.UserRepository;
import com.sharedEconomy.models.User;

@RestController
@RequestMapping(value="/api", produces="application/json")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found on :: " + userId));

		return ResponseEntity.ok().body(user);
	}

	@RequestMapping(value="/users", method=RequestMethod.POST)
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws UserNotFoundException {
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found on :: " + userId));
		/*
		 * user.setEmail(userDetails.getEmail());
		 * user.setLastName(userDetails.getLastName());
		 * user.setFirstName(userDetails.getFirstName()); user.setUpdatedOn(new Date());
		 */
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found on :: " + userId));
		
		userRepository.delete(user);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	/*
	public bool emailExists(String email) {
		var allUsers = userRepository.findAll(new Sort(Sort.Direction.ASC, "email"));
		return allUsers.
	}
		
	@Override
	public User registerNewUserAccount(User userDto) throws EmailExistsException {
	    if (emailExist(userDto.getEmail())) {
	        throw new EmailExistsException(
	          "There is an account with that email adress:" + userDto.getEmail());
	    }
	    User newUser = new User();
	    newUser.setFirstName(userDto.getFirstName());
	    newUser.setLastName(userDto.getLastName());
	     
	    newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
	     
	    newUser.setEmail(userDto.getEmail());
	    return userRepository.save(newUser);
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}*/
}
