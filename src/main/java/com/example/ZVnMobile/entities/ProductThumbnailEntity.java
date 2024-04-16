package com.example.ZVnMobile.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_thumbnail")
public class ProductThumbnailEntity extends BaseEntity{
	
	@Column(columnDefinition = "TEXT")
	private String thumbnail;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntityInThumbnail;
	
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public ProductEntity getProductEntityInThumbnail() {
		return productEntityInThumbnail;
	}

	public void setProductEntityInThumbnail(ProductEntity productEntityInThumbnail) {
		this.productEntityInThumbnail = productEntityInThumbnail;
	}
	
	
}
