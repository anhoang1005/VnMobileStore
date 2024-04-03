package com.example.ZVnMobile.payload.request;

import java.util.List;

import com.example.ZVnMobile.dto.OrderItemsDto;
import com.example.ZVnMobile.dto.OrderPaymentDto;
import com.example.ZVnMobile.dto.OrderTrackingDto;

public class CheckOutRequest {
	private String email;
	private String customerNote;
	private Double totalPrice;
	private List<OrderItemsDto> listItem;
	private OrderTrackingDto orderTracking;
	private OrderPaymentDto orderPayment;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	public List<OrderItemsDto> getListItem() {
		return listItem;
	}
	public void setListItem(List<OrderItemsDto> listItem) {
		this.listItem = listItem;
	}
	public OrderTrackingDto getOrderTracking() {
		return orderTracking;
	}
	public void setOrderTracking(OrderTrackingDto orderTracking) {
		this.orderTracking = orderTracking;
	}
	public OrderPaymentDto getOrderPayment() {
		return orderPayment;
	}
	public void setOrderPayment(OrderPaymentDto orderPayment) {
		this.orderPayment = orderPayment;
	}

}
