package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.dto.ProductColorDto;
import com.example.ZVnMobile.entities.ProductTypeEntity;


public interface IProductColorService {
	boolean insertColorOnType(ProductTypeEntity typeEntity, ProductColorDto colorDto);
}
