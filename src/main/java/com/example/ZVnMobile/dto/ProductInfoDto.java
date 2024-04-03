package com.example.ZVnMobile.dto;

public class ProductInfoDto {

	private Long productId;
	private String screen;
	private String cpu;
	private String gpu;
	private Integer weight;
	private String frontCamera;
	private String backCamera;
	private String oreraionSystem;
	private String battery;
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
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getFrontCamera() {
		return frontCamera;
	}
	public void setFrontCamera(String frontCamera) {
		this.frontCamera = frontCamera;
	}
	public String getBackCamera() {
		return backCamera;
	}
	public void setBackCamera(String backCamera) {
		this.backCamera = backCamera;
	}
	public String getOreraionSystem() {
		return oreraionSystem;
	}
	public void setOreraionSystem(String oreraionSystem) {
		this.oreraionSystem = oreraionSystem;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
}
