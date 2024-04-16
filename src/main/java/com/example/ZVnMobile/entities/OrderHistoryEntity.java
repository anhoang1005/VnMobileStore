package com.example.ZVnMobile.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders_history")
public class OrderHistoryEntity extends BaseEntity{
	
	@Column(columnDefinition = "TEXT")
	private String event;
	
	@Column(columnDefinition = "TEXT")
	private String eventDes;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderEntity orderEntityInHistory;

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

	public OrderEntity getOrderEntityInHistory() {
		return orderEntityInHistory;
	}

	public void setOrderEntityInHistory(OrderEntity orderEntityInHistory) {
		this.orderEntityInHistory = orderEntityInHistory;
	}
	
}
