package com.sharedEconomy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharedEconomy.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Returns the user which the given email belongs to
	 * @param email
	 * @return
	 */
	public User findByEmail(String email);
}
