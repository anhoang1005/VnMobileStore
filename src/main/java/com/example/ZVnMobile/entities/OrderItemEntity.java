package com.example.ZVnMobile.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders_item")
public class OrderItemEntity extends BaseEntity{
	
	@Column(columnDefinition = "VARCHAR(255)")
	private String color;
	
	@Column(columnDefinition = "DECIMAL")
	private Double price;
	
	@Column(columnDefinition = "INT")
	private int quantity;
	
	@Column(columnDefinition = "DECIMAL")
	private Double totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderEntity orderEntityByItem;
	
	@ManyToOne
	@JoinColumn(name = "productColor_id")
	private ProductColorEntity productColorEntityInItem;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public OrderEntity getOrderEntityByItem() {
		return orderEntityByItem;
	}

	public void setOrderEntityByItem(OrderEntity orderEntityByItem) {
		this.orderEntityByItem = orderEntityByItem;
	}

	public ProductColorEntity getProductColorEntityInItem() {
		return productColorEntityInItem;
	}

	public void setProductColorEntityInItem(ProductColorEntity productColorEntityInItem) {
		this.productColorEntityInItem = productColorEntityInItem;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
