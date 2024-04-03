package com.example.ZVnMobile.dto;

import java.util.Date;

public class UserInfoDto {
	private Long userId;
	private String fullName;
	private String avatar;
	private String phoneNumber;
	private String email;
	private String role;
	private Date createdAt;
	private Date getDataAt;
	
	public Date getGetDataAt() {
		return getDataAt;
	}
	public void setGetDataAt(Date getDataAt) {
		this.getDataAt = getDataAt;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
