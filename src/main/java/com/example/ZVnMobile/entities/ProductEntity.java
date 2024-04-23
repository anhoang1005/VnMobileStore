package com.example.ZVnMobile.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity{
	
	@Column(name = "title", columnDefinition = "TEXT", nullable = false)
	private String title;
	
	@Column(name = "product_slug", columnDefinition = "TEXT", unique = true, nullable = false)
	private String productSlug;
	
	@Column(name = "thumbnail", columnDefinition = "TEXT")
	private String thumbnail;
	
	@Column(name = "price", columnDefinition = "DECIMAL")
	private Double price;
	
	@Column(name = "discount", columnDefinition = "DECIMAL")
	private Double discount;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "deleted")
	private boolean deleted;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity categoryEntityInProduct;
	
	@OneToMany(mappedBy = "productEntityInType", cascade =  CascadeType.ALL)
	private List<ProductTypeEntity> listProductTypeEntities;
	
	@OneToMany(mappedBy = "productEntityInReview", cascade = CascadeType.ALL)
	private List<ProductReviewEntity> listProductReviewEntities;
	
	@OneToMany(mappedBy = "productEntityInThumbnail", cascade = CascadeType.ALL)
	private List<ProductThumbnailEntity> listProductThumbnailEntities;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private SupplierEntity supplierEntityInProduct;

	@OneToOne(mappedBy = "productEntityInInfo", cascade = CascadeType.ALL)
	private ProductInfoEntity productInfoEntityInProduct;

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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public CategoryEntity getCategoryEntityInProduct() {
		return categoryEntityInProduct;
	}

	public void setCategoryEntityInProduct(CategoryEntity categoryEntityInProduct) {
		this.categoryEntityInProduct = categoryEntityInProduct;
	}

	public List<ProductTypeEntity> getListProductTypeEntities() {
		return listProductTypeEntities;
	}

	public void setListProductTypeEntities(List<ProductTypeEntity> listProductTypeEntities) {
		this.listProductTypeEntities = listProductTypeEntities;
	}

	public List<ProductReviewEntity> getListProductReviewEntities() {
		return listProductReviewEntities;
	}

	public void setListProductReviewEntities(List<ProductReviewEntity> listProductReviewEntities) {
		this.listProductReviewEntities = listProductReviewEntities;
	}

	public List<ProductThumbnailEntity> getListProductThumbnailEntities() {
		return listProductThumbnailEntities;
	}

	public void setListProductThumbnailEntities(List<ProductThumbnailEntity> listProductThumbnailEntities) {
		this.listProductThumbnailEntities = listProductThumbnailEntities;
	}

	public SupplierEntity getSupplierEntityInProduct() {
		return supplierEntityInProduct;
	}

	public void setSupplierEntityInProduct(SupplierEntity supplierEntityInProduct) {
		this.supplierEntityInProduct = supplierEntityInProduct;
	}

	public ProductInfoEntity getProductInfoEntityInProduct() {
		return productInfoEntityInProduct;
	}

	public void setProductInfoEntityInProduct(ProductInfoEntity productInfoEntityInProduct) {
		this.productInfoEntityInProduct = productInfoEntityInProduct;
	}
	
}
