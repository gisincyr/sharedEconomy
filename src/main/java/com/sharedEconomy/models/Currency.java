package com.sharedEconomy.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tbl_currency")
@EntityListeners(AuditingEntityListener.class)
public class Currency {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "currency_shortcode", nullable = false)
	private String currencyShortCode;
	
	@Column(name = "currency", nullable = true)
	private String currency;
	
	public String getCurrencyShortcode() {
		return this.currencyShortCode;
	}
	
	public void setCurrencyShortcode(String currencyShortcode) {
		this.currencyShortCode = currencyShortcode;
	}
	
	public String getCurrency() {
		return this.currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
