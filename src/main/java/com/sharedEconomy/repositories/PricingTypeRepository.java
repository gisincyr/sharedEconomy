package com.sharedEconomy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharedEconomy.models.PricingType;

@Repository
public interface PricingTypeRepository extends JpaRepository<PricingType, Long> {

}
