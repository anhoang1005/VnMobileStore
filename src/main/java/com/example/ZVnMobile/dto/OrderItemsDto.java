package com.example.ZVnMobile.dto;

public class OrderItemsDto {

	private Long id;
	private Long orderColorId;
	private String color;
	private Double price;
	private int quantity;
	private Double totalPrice;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
//	public Long getOrderId() {
//		return orderId;
//	}
//
//	public void setOrderId(Long orderId) {
//		this.orderId = orderId;
//	}
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

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getOrderColorId() {
		return orderColorId;
	}

	public void setOrderColorId(Long orderColorId) {
		this.orderColorId = orderColorId;
	}
}
