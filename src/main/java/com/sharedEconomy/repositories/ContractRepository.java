package com.sharedEconomy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharedEconomy.models.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

}
