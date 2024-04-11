package com.example.ZVnMobile.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier")
public class SupplierEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "supplier_name", columnDefinition = "VARCHAR(150)")
	private String supplierName;
	
	@Column(name = "email", columnDefinition = "VARCHAR(150)")
	private String email;
	
	@Column(name = "phone_number", columnDefinition = "VARCHAR(20)")
	private String phoneNumber;
	
	@Column(name = "adress", columnDefinition = "TEXT")
	private String adress;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "created_at", columnDefinition = "TIMESTAMP")
	private Date createdAt;
	
	@Column(name = "deleted", columnDefinition = "BIT")
	private boolean deleted;
	
	@OneToMany(mappedBy = "supplierEntityInProduct")
	private List<ProductEntity> listProductEntities;
	
	@OneToMany(mappedBy = "supplierEntityInBill")
	private List<SupplierBillEntity> listSupplierBillEntities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getDescription() {
		return description;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProductEntity> getListProductEntities() {
		return listProductEntities;
	}

	public void setListProductEntities(List<ProductEntity> listProductEntities) {
		this.listProductEntities = listProductEntities;
	}

	public List<SupplierBillEntity> getListSupplierBillEntities() {
		return listSupplierBillEntities;
	}

	public void setListSupplierBillEntities(List<SupplierBillEntity> listSupplierBillEntities) {
		this.listSupplierBillEntities = listSupplierBillEntities;
	}
	
	
}
