package com.example.ZVnMobile.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier")
public class SupplierEntity extends BaseEntity{
	
	@Column(name = "supplier_name", columnDefinition = "VARCHAR(150)", nullable = false)
	private String supplierName;
	
	@Column(name = "email", columnDefinition = "VARCHAR(150)")
	private String email;
	
	@Column(name = "phone_number", columnDefinition = "VARCHAR(20)", nullable = false)
	private String phoneNumber;
	
	@Column(name = "adress", columnDefinition = "TEXT")
	private String adress;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "deleted", columnDefinition = "BIT", nullable = false)
	private boolean deleted;
	
	@OneToMany(mappedBy = "supplierEntityInProduct")
	private List<ProductEntity> listProductEntities;
	
	@OneToMany(mappedBy = "supplierEntityInBill")
	private List<SupplierBillEntity> listSupplierBillEntities;

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
