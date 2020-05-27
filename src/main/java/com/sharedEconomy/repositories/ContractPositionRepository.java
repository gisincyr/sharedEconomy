package com.sharedEconomy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sharedEconomy.models.ContractPosition;

@Repository
public interface ContractPositionRepository extends JpaRepository<ContractPosition, Long> {
	
	/**
	 * Returns all ContractPosition-Entities with a 5-Star-Rating
	 * @return
	 */
	@Query("SELECT cp FROM ContractPosition cp where cp.rating = 5")
	public List<ContractPosition> findAllFiveStarRatings();
	
	/**
	 * Returns all ContractPosition-Entities with the given rating
	 * @return
	 */
	@Query("SELECT cp FROM ContractPosition cp where cp.rating = ?1")
	public List<ContractPosition> findAllWithRating(int rating);
}