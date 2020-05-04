package com.sharedEconomy.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tbl_advert")
@EntityListeners(AuditingEntityListener.class)
public class Advert {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "creation_timestamp", nullable = false)
	private Date creationTimestamp;
	
	@ManyToOne
	@JoinColumn(name = "advert_type_id", referencedColumnName = "id")
	private AdvertType advertType;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "pricing_id", referencedColumnName = "id")
	private Pricing pricing;
	
	public long getId() {
        return id;
    }
	
	public void setId(long id) {
        this.id = id;
    }
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreationTimestamp() {
		return this.creationTimestamp;
	}
	
	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	
	public AdvertType getAdvertType() {
		return this.advertType;
	}
	
	public void setAdvertType(AdvertType advertType) {
		this.advertType = advertType;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Pricing getPricing() {
		return this.pricing;
	}
	
	public void setPricing(Pricing pricing) {
		this.pricing = pricing;
	}
}
