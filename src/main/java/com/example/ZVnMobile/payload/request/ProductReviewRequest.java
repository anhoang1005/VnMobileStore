package com.example.ZVnMobile.payload.request;

public class ProductReviewRequest {

	private Long id;
	private String productSlug;
	private String username;
	private int ratingStar;
	private String comment;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductSlug() {
		return productSlug;
	}
	public void setProductSlug(String productSlug) {
		this.productSlug = productSlug;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getRatingStar() {
		return ratingStar;
	}
	public void setRatingStar(int ratingStar) {
		this.ratingStar = ratingStar;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
