package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.dto.ProductTypeDto;
import com.example.ZVnMobile.entities.ProductEntity;

public interface IProductTypeService {
	boolean insertProductType(ProductEntity productEntity, ProductTypeDto typeDto);
}
