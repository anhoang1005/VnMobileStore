package com.example.ZVnMobile.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UsersEntity extends BaseEntity{

	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String fullName;

	@Column(columnDefinition = "VARCHAR(100)")
	private String avatar;

	@Column(columnDefinition = "VARCHAR(15)")
	private String phoneNumber;
	
	@Column(columnDefinition = "VARCHAR(150)", unique = true, 	nullable = false)
	private String email;

	@Column(columnDefinition = "VARCHAR(255)", nullable = false)
	private String password;
	
	@Column(columnDefinition = "VARCHAR(20)", nullable = false)
	private String role;

	@Column(columnDefinition = "VARCHAR(64)")
	private String verifyCode;

	@Column(nullable = false)
	private boolean enable;
	
	@Column(nullable = false)
	private boolean deleted;
	
	@OneToMany(mappedBy = "usersEntityInOrder", cascade = CascadeType.ALL)
	private List<OrderEntity> listOrderEntities;
	
	@OneToMany(mappedBy = "usersEntityInReview", cascade = CascadeType.ALL)
	private List<ProductReviewEntity> listProductReviewEntities;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<OrderEntity> getListOrderEntities() {
		return listOrderEntities;
	}

	public void setListOrderEntities(List<OrderEntity> listOrderEntities) {
		this.listOrderEntities = listOrderEntities;
	}

	public List<ProductReviewEntity> getListProductReviewEntities() {
		return listProductReviewEntities;
	}

	public void setListProductReviewEntities(List<ProductReviewEntity> listProductReviewEntities) {
		this.listProductReviewEntities = listProductReviewEntities;
	}
	
	
}
