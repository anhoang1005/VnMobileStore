package com.example.ZVnMobile.convert;

import org.springframework.stereotype.Component;

import com.example.ZVnMobile.dto.ProductColorDto;
import com.example.ZVnMobile.entities.ProductColorEntity;

@Component
public class ProductColorConverter {

	public ProductColorDto typeColorEntityToTypeColorDto(ProductColorEntity entity) {
		ProductColorDto dto = new ProductColorDto();
		dto.setId(entity.getId());
		dto.setColor(entity.getColor());
		dto.setSoldQuantity(entity.getSoldQuantity());
		dto.setInventoryQuantity(entity.getInventoryQuantity());
		
		return dto;
	}
}
