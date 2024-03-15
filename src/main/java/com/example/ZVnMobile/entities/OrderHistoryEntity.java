package com.example.ZVnMobile.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders_history")
public class OrderHistoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TEXT")
	private String event;
	
	@Column(columnDefinition = "TEXT")
	private String eventDes;
	
	@Column(columnDefinition = "TIMESTAMP")
	private Date createdAt;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderEntity orderEntityInHistory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public OrderEntity getOrderEntityInHistory() {
		return orderEntityInHistory;
	}

	public void setOrderEntityInHistory(OrderEntity orderEntityInHistory) {
		this.orderEntityInHistory = orderEntityInHistory;
	}
	
}
