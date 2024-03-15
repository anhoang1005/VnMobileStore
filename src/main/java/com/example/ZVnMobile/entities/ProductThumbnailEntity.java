package com.example.ZVnMobile.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_thumbnail")
public class ProductThumbnailEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "VARCHAR(150)")
	private String thumbnail;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntityInThumbnail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
