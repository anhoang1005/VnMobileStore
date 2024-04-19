package com.example.ZVnMobile.service.impl;

import java.util.Date;

import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.BillRequest;

public interface ISupplierBillService {
	DataResponse getAllSupplierBill(int pageNumber);
	DataResponse getByCreatedAt(Date createdAt);
	DataResponse createBill(BillRequest bill);
}
