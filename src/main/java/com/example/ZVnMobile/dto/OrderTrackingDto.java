package com.example.ZVnMobile.dto;

public class OrderTrackingDto {
	//private Long orderId;
	private String carrier;
	private String url;
	private Double fee;
	private String shippingCode;
	private String customerProvince;
	private String customerDistrict;
	private String customerWard;
	private String customerAdress;
	private String customerName;
	private String customerTelephone;
	private String customerEmail;
//	public Long getOrderId() {
//		return orderId;
//	}
//	public void setOrderId(Long orderId) {
//		this.orderId = orderId;
//	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public String getShippingCode() {
		return shippingCode;
	}
	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}
	public String getCustomerProvince() {
		return customerProvince;
	}
	public void setCustomerProvince(String customerProvince) {
		this.customerProvince = customerProvince;
	}
	public String getCustomerDistrict() {
		return customerDistrict;
	}
	public void setCustomerDistrict(String customerDistrict) {
		this.customerDistrict = customerDistrict;
	}
	public String getCustomerWard() {
		return customerWard;
	}
	public void setCustomerWard(String customerWard) {
		this.customerWard = customerWard;
	}
	public String getCustomerAdress() {
		return customerAdress;
	}
	public void setCustomerAdress(String customerAdress) {
		this.customerAdress = customerAdress;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerTelephone() {
		return customerTelephone;
	}
	public void setCustomerTelephone(String customerTelephone) {
		this.customerTelephone = customerTelephone;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
}
