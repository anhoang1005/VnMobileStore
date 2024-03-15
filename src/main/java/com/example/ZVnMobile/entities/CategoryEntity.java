package com.example.ZVnMobile.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "VARCHAR(100)")
	private String categoryName;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@OneToMany(mappedBy = "categoryEntityInProduct")
	private List<ProductEntity> listProductEntities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProductEntity> getListProductEntities() {
		return listProductEntities;
	}

	public void setListProductEntities(List<ProductEntity> listProductEntities) {
		this.listProductEntities = listProductEntities;
	}
	
}
