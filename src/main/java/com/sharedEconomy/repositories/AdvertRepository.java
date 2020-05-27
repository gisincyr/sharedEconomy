package com.sharedEconomy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sharedEconomy.models.Advert;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

	/**
	 * Returns the amount of adverts that make use of the two separate pricing types
	 * @return
	 */
	@Query("SELECT pt.type, count(1) FROM Advert adv "
			+ "join Pricing p on adv.pricing.id = p.id "
			+ "join PricingType pt on p.pricingType.id = pt.id "
			+ "group by pt.type")
	public List<Object> GetCountAdvertsForEachPricingType();
}
