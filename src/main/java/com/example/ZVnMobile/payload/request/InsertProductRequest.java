package com.example.ZVnMobile.payload.request;

import java.util.List;

import com.example.ZVnMobile.dto.ProductInfoDto;
import com.example.ZVnMobile.dto.ProductThumbnailDto;
import com.example.ZVnMobile.dto.ProductTypeDto;

public class InsertProductRequest {
	private String title;
	private String productSlug;
	private String thumbnail;
	private Double price;
	private Double discount;
	private String description;
	private Long categoryId;
	private Long supplierId;
	private ProductInfoDto productInfo;
	private List<ProductThumbnailDto> listThumbnail;
	private List<ProductTypeDto> listProdductType;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public ProductInfoDto getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(ProductInfoDto productInfo) {
		this.productInfo = productInfo;
	}
	public List<ProductThumbnailDto> getListThumbnail() {
		return listThumbnail;
	}
	public void setListThumbnail(List<ProductThumbnailDto> listThumbnail) {
		this.listThumbnail = listThumbnail;
	}
	public List<ProductTypeDto> getListProdductType() {
		return listProdductType;
	}
	public void setListProdductType(List<ProductTypeDto> listProdductType) {
		this.listProdductType = listProdductType;
	}
}
