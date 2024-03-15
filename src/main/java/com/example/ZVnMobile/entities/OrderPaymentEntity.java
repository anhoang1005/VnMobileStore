package com.example.ZVnMobile.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_payment")
public class OrderPaymentEntity {
	
	@Id
	private Long orderId;
	
	@Column(columnDefinition = "VARCHAR(255)")
	private String gateway;
	
	@Column(columnDefinition = "VARCHAR(255)")
	private String bankCode;
	
	@Column(columnDefinition = "TEXT")
	private String paymentInfo;
	
	@Column(columnDefinition = "VARCHAR(255)")
	private String status;
	
	@OneToOne
	@JoinColumn(name = "order_id", unique = true)
	@MapsId
	private OrderEntity orderEntityInPayMent;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OrderEntity getOrderEntityInPayMent() {
		return orderEntityInPayMent;
	}

	public void setOrderEntityInPayMent(OrderEntity orderEntityInPayMent) {
		this.orderEntityInPayMent = orderEntityInPayMent;
	}
	
}
