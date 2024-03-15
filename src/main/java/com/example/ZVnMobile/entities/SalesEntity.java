package com.example.ZVnMobile.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales")
public class SalesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "VARCHAR(255)")
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String details;
	
	@Column(columnDefinition = "FLOAT")
	private Double percent;
	
	@Column(columnDefinition = "DECIMAL")
	private Double maxValue;
	
	@Column(columnDefinition = "TIMESTAMP")
	private Date created_at;
	
	@Column(columnDefinition = "TIMESTAMP")
	private Date expiration_at;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getExpiration_at() {
		return expiration_at;
	}

	public void setExpiration_at(Date expiration_at) {
		this.expiration_at = expiration_at;
	}
	
	
}
