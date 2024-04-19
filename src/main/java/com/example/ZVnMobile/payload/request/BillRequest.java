package com.example.ZVnMobile.payload.request;

import java.util.List;

public class BillRequest {
	
	private String emailUser;
	private Long supplierId;
	private List<BillItemsRequest> listBillItems;
	public String getEmailUser() {
		return emailUser;
	}
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public List<BillItemsRequest> getListBillItems() {
		return listBillItems;
	}
	public void setListBillItems(List<BillItemsRequest> listBillItems) {
		this.listBillItems = listBillItems;
	}
}
