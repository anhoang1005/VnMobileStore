package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.payload.DataResponse;

public interface ISupplierService {
	DataResponse getAllSupplier();
	DataResponse getSupplierById(Long id);
}
