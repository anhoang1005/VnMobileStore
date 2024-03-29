package com.example.ZVnMobile.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "VARCHAR(255)")
	private String status;
	
	@Column(columnDefinition = "DECIMAL")
	private Double total_price;
	
	@Column(columnDefinition = "TEXT")
	private String customerNote;
	
	@Column(columnDefinition = "TIMESTAMP")
	private Date created_at;
	
	@Column(columnDefinition = "TIMESTAMP")
	private Date delivery_at;
	
	@Column(columnDefinition = "TIMESTAMP")
	private Date update_at;
	
	@OneToMany(mappedBy = "orderEntityInHistory")
	private List<OrderHistoryEntity> listOrderHistoryEntities;
	
	@OneToMany(mappedBy = "orderEntityByItem")
	private List<OrderItemEntity> listOrderItemEntities;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UsersEntity usersEntityInOrder;
	
	@OneToOne(mappedBy = "orderEntityInPayMent")
	//@JoinColumn(name = "payment_id", referencedColumnName = "id")
	private OrderPaymentEntity orderPaymentEntityInOrder;
	
	@OneToOne(mappedBy = "orderEntityInTracking")
	private OrderTrackingEntity orderTrackingEntityInOrder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public String getCustomerNote() {
		return customerNote;
	}

	public void setCustomerNote(String customerNote) {
		this.customerNote = customerNote;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getDelivery_at() {
		return delivery_at;
	}

	public void setDelivery_at(Date delivery_at) {
		this.delivery_at = delivery_at;
	}

	public Date getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(Date update_at) {
		this.update_at = update_at;
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
