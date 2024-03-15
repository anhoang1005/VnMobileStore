package com.example.ZVnMobile.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_tracking")
public class OrderTrackingEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	@Column(columnDefinition = "VARCHAR(255)")
	private String carrier;
	
	@Column(columnDefinition = "TEXT")
	private String url;
	
	@Column(columnDefinition = "DECIMAL")
	private Double fee;
	
	@Column(columnDefinition = "VARCHAR(50)")
	private String shippingCode;
	
	@Column(columnDefinition = "VARCHAR(150)")
	private String customerProvince;
	
	@Column(columnDefinition = "VARCHAR(150)")
	private String customerDistrict;
	
	@Column(columnDefinition = "VARCHAR(150)")
	private String customerWard;
	
	@Column(columnDefinition = "VARCHAR(150)")
	private String customerAdress;
	
	@Column(columnDefinition = "VARCHAR(150)")
	private String customerName;
	
	@Column(columnDefinition = "VARCHAR(15)")
	private String customerTelephone;
	
	@Column(columnDefinition = "VARCHAR(150)")
	private String customerEmail;
	
	@OneToOne
	@JoinColumn(name = "order_id", unique = true)
	@MapsId
	private OrderEntity orderEntityInTracking;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public String getCustomerProvince() {
		return customerProvince;
	}

	public void setCustomerProvince(String customerProvince) {
		this.customerProvince = customerProvince;
	}

	public String getCustomerDistrict() {
		return customerDistrict;
	}

	public void setCustomerDistrict(String customerDistrict) {
		this.customerDistrict = customerDistrict;
	}

	public String getCustomerWard() {
		return customerWard;
	}

	public void setCustomerWard(String customerWard) {
		this.customerWard = customerWard;
	}

	public String getCustomerAdress() {
		return customerAdress;
	}

	public void setCustomerAdress(String customerAdress) {
		this.customerAdress = customerAdress;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerTelephone() {
		return customerTelephone;
	}

	public void setCustomerTelephone(String customerTelephone) {
		this.customerTelephone = customerTelephone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public OrderEntity getOrderEntityInTracking() {
		return orderEntityInTracking;
	}

	public void setOrderEntityInTracking(OrderEntity orderEntityInTracking) {
		this.orderEntityInTracking = orderEntityInTracking;
	}
}
