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
@Table(name = "tbl_advert_type")
@EntityListeners(AuditingEntityListener.class)
public class AdvertType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "advert_type", nullable = false)
	private String advertType;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	public long getId() {
        return id;
    }
	
	public void setId(long id) {
        this.id = id;
    }
	
	public String getAdvertType() {
		return this.advertType;
	}
	
	public void setAdvertType(String advertType) {
		this.advertType = advertType;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
