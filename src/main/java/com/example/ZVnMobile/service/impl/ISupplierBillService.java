package com.example.ZVnMobile.service.impl;

import java.util.Date;

import com.example.ZVnMobile.payload.DataResponse;

public interface ISupplierBillService {
	DataResponse getAllSupplierBill(int pageNumber);
	DataResponse getByCreatedAt(Date createdAt);
}
