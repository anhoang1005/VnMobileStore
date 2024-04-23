package com.example.ZVnMobile.service.impl;

import java.util.List;

import com.example.ZVnMobile.dto.ProductTypeDto;
import com.example.ZVnMobile.entities.ProductEntity;
import com.example.ZVnMobile.payload.DataResponse;

public interface IProductTypeService {
	boolean insertListType(ProductEntity productEntity, List<ProductTypeDto> listType);
	boolean insertProductType(ProductEntity productEntity, ProductTypeDto typeDto);
	DataResponse getTypeByProductId(Long id); 
	boolean insertProductType(ProductTypeDto typeDto);
}
