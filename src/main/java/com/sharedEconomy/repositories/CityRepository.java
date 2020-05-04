package com.sharedEconomy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharedEconomy.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}

