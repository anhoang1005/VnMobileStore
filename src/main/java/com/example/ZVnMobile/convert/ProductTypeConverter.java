package com.example.ZVnMobile.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ZVnMobile.dto.ProductColorDto;
import com.example.ZVnMobile.dto.ProductTypeDto;
import com.example.ZVnMobile.entities.ProductColorEntity;
import com.example.ZVnMobile.entities.ProductTypeEntity;

@Component
public class ProductTypeConverter {
	
	@Autowired
	private ProductColorConverter typeColorConverter;

	public ProductTypeDto productTypeEntityToDto(ProductTypeEntity entity) {
		ProductTypeDto dto = new ProductTypeDto();
		dto.setId(entity.getId());
		dto.setBasePrice(entity.getBasePrice());
		dto.setDiscount(entity.getDiscount());
		dto.setPrice(entity.getPrice());
		dto.setRam(entity.getRam());
		dto.setRoom(entity.getRoom());
		
		List<ProductColorDto> listColor = new ArrayList<>();
		for(ProductColorEntity colorEntity : entity.getListTypeColorEntities()) {
			ProductColorDto colorDto = typeColorConverter.typeColorEntityToTypeColorDto(colorEntity);
			listColor.add(colorDto);
		}
		dto.setListTypeColor(listColor);
		return dto;
	}
}
