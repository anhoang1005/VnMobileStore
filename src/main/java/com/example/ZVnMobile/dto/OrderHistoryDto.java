package com.example.ZVnMobile.dto;

import java.util.Date;

public class OrderHistoryDto {
	private Long id;
//	private Long orderId;
	private String event;
	private String eventDes;
	private Date createdAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
//	public Long getOrderId() {
//		return orderId;
//	}
//	public void setOrderId(Long orderId) {
//		this.orderId = orderId;
//	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEventDes() {
		return eventDes;
	}
	public void setEventDes(String eventDes) {
		this.eventDes = eventDes;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
