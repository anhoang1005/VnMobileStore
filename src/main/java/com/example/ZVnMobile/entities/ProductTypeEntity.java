package com.example.ZVnMobile.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_type")
public class ProductTypeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "VARCHAR(100)")
	private String color;
	
	@Column(columnDefinition = "VARCHAR(255)")
	private String version;
	
	@Column(columnDefinition = "INT")
	private int soldQuantity;
	
	@Column(columnDefinition = "INT")
	private int inventoryQuantity;
	
	@Column(columnDefinition = "DECIMAL")
	private Double basePrice;
	
	@Column(columnDefinition = "DECIMAL")
	private Double price;
	
	@Column(columnDefinition = "DECIMAL")
	private Double discount;
	
	@OneToMany(mappedBy = "productTypeEntityInItem")
	private List<OrderItemEntity> listOrderItemEntities;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntityInType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(int soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public int getInventoryQuantity() {
		return inventoryQuantity;
	}

	public void setInventoryQuantity(int inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public List<OrderItemEntity> getListOrderItemEntities() {
		return listOrderItemEntities;
	}

	public void setListOrderItemEntities(List<OrderItemEntity> listOrderItemEntities) {
		this.listOrderItemEntities = listOrderItemEntities;
	}

	public ProductEntity getProductEntityInType() {
		return productEntityInType;
	}

	public void setProductEntityInType(ProductEntity productEntityInType) {
		this.productEntityInType = productEntityInType;
	}
	
	
	
}
