package com.example.ZVnMobile.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", columnDefinition = "TEXT")
	private String title;
	
	@Column(name = "product_slug", columnDefinition = "TEXT")
	private String productSlug;
	
	@Column(name = "thumbnail", columnDefinition = "VARCHAR(150)")
	private String thumbnail;
	
	@Column(name = "base_price", columnDefinition = "DECIMAL")
	private Double basePrice;
	
	@Column(name = "price", columnDefinition = "DECIMAL")
	private Double price;
	
	@Column(name = "discont", columnDefinition = "DECIMAL")
	private Double discount;
	
	@Column(name = "screen", columnDefinition = "VARCHAR(255)")
	private String screen;
	
	@Column(name = "rear_camera", columnDefinition = "VARCHAR(255)")
	private String rearCamera;
	
	@Column(name = "front_camera", columnDefinition = "VARCHAR(255)")
	private String frontCamera;
	
	@Column(name = "cpu", columnDefinition = "VARCHAR(255)")
	private String cpu;
	
	@Column(name = "gpu", columnDefinition = "VARCHAR(255)")
	private String gpu;
	
	@Column(name = "weight", columnDefinition = "INT")
	private int weight;
	
	@Column(name = "operating_system", columnDefinition = "VARCHAR(150)")
	private String operatingSystem;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "created_at", columnDefinition = "DATETIME")
	private Date createdAt;
	
	@Column(name = "updated_at", columnDefinition = "DATETIME")
	private Date updatedAt;
	
	@Column(name = "deleted")
	private boolean deleted;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity categoryEntityInProduct;
	
	@OneToMany(mappedBy = "productEntityInType")
	private List<ProductTypeEntity> listProductTypeEntities;
	
	@OneToMany(mappedBy = "productEntityInReview")
	private List<ProductReviewEntity> listProductReviewEntities;
	
	@OneToMany(mappedBy = "productEntityInThumbnail")
	private List<ProductThumbnailEntity> listProductThumbnailEntities;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private SupplierEntity supplierEntityInProduct;

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

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
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

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getRearCamera() {
		return rearCamera;
	}

	public void setRearCamera(String rearCamera) {
		this.rearCamera = rearCamera;
	}

	public String getFrontCamera() {
		return frontCamera;
	}

	public void setFrontCamera(String frontCamera) {
		this.frontCamera = frontCamera;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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
	
}
