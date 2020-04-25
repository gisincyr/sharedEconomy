package com.sharedEconomy.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tbl_pricing")
@EntityListeners(AuditingEntityListener.class)
public class Pricing {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "price", nullable = false)
	private float price;
	
	@ManyToOne
	@JoinColumn(name = "pricing_type_id", referencedColumnName = "id")
	private PricingType pricingType;
	
	@ManyToOne
	@JoinColumn(name = "currency_id", referencedColumnName = "id")
	private Currency currency;
	
	public float getPrice() {
		return this.price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public PricingType getPricingType() {
		return this.pricingType;
	}
	
	public void setPricingType(PricingType pricingType) {
		this.pricingType = pricingType;
	}
	
	public Currency getCurrency() {
		return this.currency;
	}
	
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
