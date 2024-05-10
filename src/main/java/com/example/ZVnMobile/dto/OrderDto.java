package com.example.ZVnMobile.dto;

import java.util.Date;

public class OrderDto {
	private Long orderId;
	private Long userId;
	private String orderCode;
	private String customerNote;
	private Double totalPrice;
	private String status;
	private Date createdAt;
	private String createdBy;
	private Date updatedAt;
	private Date deliveryAt;
	private Object orderTracking;
	private Object orderPayment;
	private Object orderHistory;
	private Object orderItems;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCustomerNote() {
		return customerNote;
	}
	public void setCustomerNote(String customerNote) {
		this.customerNote = customerNote;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Date getDeliveryAt() {
		return deliveryAt;
	}
	public void setDeliveryAt(Date deliveryAt) {
		this.deliveryAt = deliveryAt;
	}
	public Object getOrderTracking() {
		return orderTracking;
	}
	public void setOrderTracking(Object orderTracking) {
		this.orderTracking = orderTracking;
	}
	public Object getOrderPayment() {
		return orderPayment;
	}
	public void setOrderPayment(Object orderPayment) {
		this.orderPayment = orderPayment;
	}
	public Object getOrderHistory() {
		return orderHistory;
	}
	public void setOrderHistory(Object orderHistory) {
		this.orderHistory = orderHistory;
	}
	public Object getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Object orderItems) {
		this.orderItems = orderItems;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
