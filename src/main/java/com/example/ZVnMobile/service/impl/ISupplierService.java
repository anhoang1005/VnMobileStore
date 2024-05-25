package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.dto.SupplierDto;
import com.example.ZVnMobile.payload.DataResponse;

public interface ISupplierService {
	DataResponse getAllSupplier();
	DataResponse getSupplierById(Long id);
	DataResponse insertSupplier(SupplierDto supplierDto);
	DataResponse updateSupplier(SupplierDto supplierDto);
	DataResponse lockOrUnlockSupplier(Long id, boolean status);
	DataResponse getByStatus(Boolean deleted);
}
