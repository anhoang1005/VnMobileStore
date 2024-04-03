package com.example.ZVnMobile.dto;

public class ProductCardDto {
	
	private Long id;
	private String title;
	private String productSlug;
	private String thumbnail;
	private Double price;
	private Double discount;
	private Double ratingStar;
	private Object productInfo;
	private Object listType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProductSlug() {
		return productSlug;
	}
	public void setProductSlug(String productSlug) {
		this.productSlug = productSlug;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getRatingStar() {
		return ratingStar;
	}
	public void setRatingStar(Double ratingStar) {
		this.ratingStar = ratingStar;
	}
	public Object getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(Object productInfo) {
		this.productInfo = productInfo;
	}
	public Object getListType() {
		return listType;
	}
	public void setListType(Object listType) {
		this.listType = listType;
	}
}
