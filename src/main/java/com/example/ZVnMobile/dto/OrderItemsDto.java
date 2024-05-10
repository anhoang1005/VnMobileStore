package com.example.ZVnMobile.dto;

public class OrderItemsDto {

	private Long id;
	private Long orderColorId;
	private String thumbnail;
	private String title;
	private String color;
	private String version;
	private Double price;
	private int quantity;
	private Double totalPrice;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrderColorId() {
		return orderColorId;
	}
	public void setOrderColorId(Long orderColorId) {
		this.orderColorId = orderColorId;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
}
