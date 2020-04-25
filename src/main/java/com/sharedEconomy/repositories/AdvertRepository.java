package com.sharedEconomy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharedEconomy.models.Advert;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

}
