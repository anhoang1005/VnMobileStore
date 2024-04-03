package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.dto.ProductInfoDto;
import com.example.ZVnMobile.entities.ProductEntity;
import com.example.ZVnMobile.payload.DataResponse;

public interface IProductInfoService {
	DataResponse getByProductId(Long id);
	boolean insertProductInfo(ProductEntity productEntity, ProductInfoDto infoDto);
}
