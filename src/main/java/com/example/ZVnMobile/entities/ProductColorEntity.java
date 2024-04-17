package com.example.ZVnMobile.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_color")
public class ProductColorEntity extends BaseEntity{
	
	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String color;
	
	@Column(columnDefinition = "INT")
	private Integer soldQuantity;
	
	@Column(columnDefinition = "INT")
	private Integer inventoryQuantity;
	
//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "type_id")
	private ProductTypeEntity productTypeEntityInColor;
	
	@OneToMany(mappedBy = "productColorEntityInItem")
	private List<OrderItemEntity> listOrderItemEntitiesInColor;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(Integer soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public Integer getInventoryQuantity() {
		return inventoryQuantity;
	}

	public void setInventoryQuantity(Integer inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

	public ProductTypeEntity getProductTypeEntityInColor() {
		return productTypeEntityInColor;
	}

	public void setProductTypeEntityInColor(ProductTypeEntity productTypeEntityInColor) {
		this.productTypeEntityInColor = productTypeEntityInColor;
	}

	public List<OrderItemEntity> getListOrderItemEntitiesInColor() {
		return listOrderItemEntitiesInColor;
	}

	public void setListOrderItemEntitiesInColor(List<OrderItemEntity> listOrderItemEntitiesInColor) {
		this.listOrderItemEntitiesInColor = listOrderItemEntitiesInColor;
	}
}
