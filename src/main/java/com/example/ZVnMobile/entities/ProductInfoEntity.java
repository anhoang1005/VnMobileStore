package com.example.ZVnMobile.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_info")
public class ProductInfoEntity {

	@Id
	private Long productId;
	
	@Column(name = "screen", columnDefinition = "VARCHAR(255)")
	private String screen;
	
	@Column(name = "back_camera", columnDefinition = "VARCHAR(255)")
	private String backCamera;
	
	@Column(name = "front_camera", columnDefinition = "VARCHAR(255)")
	private String frontCamera;
	
	@Column(name = "cpu", columnDefinition = "VARCHAR(255)")
	private String cpu;
	
	@Column(name = "gpu", columnDefinition = "VARCHAR(255)")
	private String gpu;
	
	@Column(name = "operating_system", columnDefinition = "VARCHAR(150)")
	private String operatingSystem;
	
	@Column(name = "weight", columnDefinition = "INT")
	private Integer weight;
	
	@Column(columnDefinition = "VARCHAR(100)")
	private String battery;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "product_id", unique = true)
	@MapsId
	private ProductEntity productEntityInInfo;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getBackCamera() {
		return backCamera;
	}

	public void setBackCamera(String backCamera) {
		this.backCamera = backCamera;
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

	public String getGpu() {
		return gpu;
	}

	public void setGpu(String gpu) {
		this.gpu = gpu;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public ProductEntity getProductEntityInInfo() {
		return productEntityInInfo;
	}

	public void setProductEntityInInfo(ProductEntity productEntityInInfo) {
		this.productEntityInInfo = productEntityInInfo;
	}
	
}
