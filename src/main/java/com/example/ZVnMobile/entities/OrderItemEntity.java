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
@Table(name = "orders_item")
public class OrderItemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TEXT")
	private String type;
	
	@Column(columnDefinition = "DECIMAL")
	private Double price;
	
	@Column(columnDefinition = "INT")
	private int quantity;
	
	@Column(columnDefinition = "DECIMAL")
	private Double total_price;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderEntity orderEntityByItem;
	
	@ManyToOne
	@JoinColumn(name = "productType_id")
	private ProductTypeEntity productTypeEntityInItem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public OrderEntity getOrderEntityByItem() {
		return orderEntityByItem;
	}

	public void setOrderEntityByItem(OrderEntity orderEntityByItem) {
		this.orderEntityByItem = orderEntityByItem;
	}

	public ProductTypeEntity getProductTypeEntityInItem() {
		return productTypeEntityInItem;
	}

	public void setProductTypeEntityInItem(ProductTypeEntity productTypeEntityInItem) {
		this.productTypeEntityInItem = productTypeEntityInItem;
	}
	
}
