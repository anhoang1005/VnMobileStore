package com.example.ZVnMobile.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity{
	
	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String categoryName;
	
	@Column(columnDefinition = "TEXT")
	private String images;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(columnDefinition = "BIT")
	private boolean deleted;
	
	@OneToMany(mappedBy = "categoryEntityInProduct")
	private List<ProductEntity> listProductEntities;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<ProductEntity> getListProductEntities() {
		return listProductEntities;
	}

	public void setListProductEntities(List<ProductEntity> listProductEntities) {
		this.listProductEntities = listProductEntities;
	}
	
}
