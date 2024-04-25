package com.example.ZVnMobile.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{
	
	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String orderCode;
	
	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String status;
	
	@Column(columnDefinition = "DECIMAL", nullable = false)
	private Double totalPrice;
	
	@Column(columnDefinition = "TEXT")
	private String customerNote;
	
	@Column(columnDefinition = "TIMESTAMP")
	private Date deliveryAt;
	
	@OneToMany(mappedBy = "orderEntityInHistory", cascade = CascadeType.ALL)
	private List<OrderHistoryEntity> listOrderHistoryEntities;
	
	@OneToMany(mappedBy = "orderEntityByItem", cascade = CascadeType.ALL)
	private List<OrderItemEntity> listOrderItemEntities;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UsersEntity usersEntityInOrder;
	
	@OneToOne(mappedBy = "orderEntityInPayMent", cascade = CascadeType.ALL)
	private OrderPaymentEntity orderPaymentEntityInOrder;
	
	@OneToOne(mappedBy = "orderEntityInTracking", cascade = CascadeType.ALL)
	private OrderTrackingEntity orderTrackingEntityInOrder;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCustomerNote() {
		return customerNote;
	}

	public void setCustomerNote(String customerNote) {
		this.customerNote = customerNote;
	}

	public Date getDeliveryAt() {
		return deliveryAt;
	}

	public void setDeliveryAt(Date deliveryAt) {
		this.deliveryAt = deliveryAt;
	}

	public List<OrderHistoryEntity> getListOrderHistoryEntities() {
		return listOrderHistoryEntities;
	}

	public void setListOrderHistoryEntities(List<OrderHistoryEntity> listOrderHistoryEntities) {
		this.listOrderHistoryEntities = listOrderHistoryEntities;
	}

	public List<OrderItemEntity> getListOrderItemEntities() {
		return listOrderItemEntities;
	}

	public void setListOrderItemEntities(List<OrderItemEntity> listOrderItemEntities) {
		this.listOrderItemEntities = listOrderItemEntities;
	}

	public UsersEntity getUsersEntityInOrder() {
		return usersEntityInOrder;
	}

	public void setUsersEntityInOrder(UsersEntity usersEntityInOrder) {
		this.usersEntityInOrder = usersEntityInOrder;
	}

	public OrderPaymentEntity getOrderPaymentEntityInOrder() {
		return orderPaymentEntityInOrder;
	}

	public void setOrderPaymentEntityInOrder(OrderPaymentEntity orderPaymentEntityInOrder) {
		this.orderPaymentEntityInOrder = orderPaymentEntityInOrder;
	}

	public OrderTrackingEntity getOrderTrackingEntityInOrder() {
		return orderTrackingEntityInOrder;
	}

	public void setOrderTrackingEntityInOrder(OrderTrackingEntity orderTrackingEntityInOrder) {
		this.orderTrackingEntityInOrder = orderTrackingEntityInOrder;
	}	
}
