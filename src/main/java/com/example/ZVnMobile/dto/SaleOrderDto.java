package com.example.ZVnMobile.dto;

import java.math.BigDecimal;

public class SaleOrderDto {
	private int year;
    private int month;
    private long totalOrders;
    private BigDecimal revenue;
    
	public SaleOrderDto() {
		super();
	}
	
	public SaleOrderDto(int year, int month, long totalOrders, BigDecimal revenue) {
        this.year = year;
        this.month = month;
        this.totalOrders = totalOrders;
        this.revenue = revenue;
    }


	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public long getTotalOrders() {
		return totalOrders;
	}
	public void setTotalOrders(long totalOrders) {
		this.totalOrders = totalOrders;
	}

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}
}
