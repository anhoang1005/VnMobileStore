package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.dto.ProductTypeDto;
import com.example.ZVnMobile.entities.ProductEntity;
import com.example.ZVnMobile.payload.DataResponse;

public interface IProductTypeService {
	boolean insertProductType(ProductEntity productEntity, ProductTypeDto typeDto);
	DataResponse getTypeByProductId(Long id); 
}
