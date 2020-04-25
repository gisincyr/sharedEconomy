package com.sharedEconomy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharedEconomy.models.ContractPosition;

@Repository
public interface ContractPositionRepository extends JpaRepository<ContractPosition, Long> {

}