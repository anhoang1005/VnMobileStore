package com.example.ZVnMobile.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier_bill")
public class SupplierBillEntity extends BaseEntity{
	
	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String title;
	
	@Column(columnDefinition = "DECIMAL", nullable = false)
	private Double totalPrice;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private SupplierEntity supplierEntityInBill;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SupplierEntity getSupplierEntityInBill() {
		return supplierEntityInBill;
	}

	public void setSupplierEntityInBill(SupplierEntity supplierEntityInBill) {
		this.supplierEntityInBill = supplierEntityInBill;
	}
	
	
}
