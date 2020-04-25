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
@Table(name = "tbl_contract_position")
@EntityListeners(AuditingEntityListener.class)
public class ContractPosition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "rating", nullable = true)
	private int rating;
	
	@ManyToOne
	@JoinColumn(name = "advert_id", referencedColumnName = "id")
	private Advert advert;
	
	@ManyToOne
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	private Contract contract;
	
	public long getId() {
        return id;
    }
	
	public void setId(long id) {
        this.id = id;
    }
	
	public int getRating() {
		return this.rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public Advert getAdvert() {
		return this.advert;
	}
	
	public void setAdvert(Advert advert) {
		this.advert = advert;
	}
	
	public Contract getContract() {
		return this.contract;
	}
	
	public void setContract(Contract contract) {
		this.contract = contract;
	}
}
