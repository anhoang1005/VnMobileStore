package com.example.ZVnMobile.dto;

import java.util.List;

public class ProductTypeDto {
	private Long id;
	private Integer ram;
	private Integer room;
	private Double price;
	private Double discount;
	private Double basePrice;
	private List<ProductColorDto> listTypeColor;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getRam() {
		return ram;
	}
	public void setRam(Integer ram) {
		this.ram = ram;
	}
	public Integer getRoom() {
		return room;
	}
	public void setRoom(Integer room) {
		this.room = room;
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
	public Double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	public List<ProductColorDto> getListTypeColor() {
		return listTypeColor;
	}
	public void setListTypeColor(List<ProductColorDto> listTypeColor) {
		this.listTypeColor = listTypeColor;
	}
}
