package com.sharedEconomy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharedEconomy.models.Pricing;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Long> {

}

