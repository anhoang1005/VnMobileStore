package com.example.ZVnMobile.dto;

public class ProductOfSupplierDto {
	private Long id;
	private String thumbnail;
	private String title;
	private String category;
	private int quantitySold;
	private double rateStar;
	private int versionQuantity;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQuantitySold() {
		return quantitySold;
	}
	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}
	public double getRateStar() {
		return rateStar;
	}
	public void setRateStar(double rateStar) {
		this.rateStar = rateStar;
	}
	public int getVersionQuantity() {
		return versionQuantity;
	}
	public void setVersionQuantity(int versionQuantity) {
		this.versionQuantity = versionQuantity;
	}
	
}
