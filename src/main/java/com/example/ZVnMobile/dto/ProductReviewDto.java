package com.example.ZVnMobile.dto;

import java.util.Date;

public class ProductReviewDto {

	private Long ReviewId;
	private Long userId;
	private Long productId;
	private String username;
	private String fullName;
	private String avatar;
	private int rateStar;
	private String comment;
	private Integer like;
	private String createdAt;
	private Date updatedAt;
	
	
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Long getReviewId() {
		return ReviewId;
	}
	public void setReviewId(Long reviewId) {
		ReviewId = reviewId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getRateStar() {
		return rateStar;
	}
	public void setRateStar(int rateStar) {
		this.rateStar = rateStar;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getLike() {
		return like;
	}
	public void setLike(Integer integer) {
		this.like = integer;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
