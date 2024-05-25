package com.example.ZVnMobile.dto;

import java.math.BigDecimal;

public class Sale10YearDto {
	
	private Integer year;
	private BigDecimal totalPrice;
	private Long orderCount;
	public Sale10YearDto() {
	}
	public Sale10YearDto(Integer year, BigDecimal totalPrice, Long orderCount) {
		super();
		this.year = year;
		this.totalPrice = totalPrice;
		this.orderCount = orderCount;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Long getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Long orderCount) {
		this.orderCount = orderCount;
	}
}
