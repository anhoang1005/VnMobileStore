package com.example.ZVnMobile.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ZVnMobile.dto.ProductColorDto;
import com.example.ZVnMobile.dto.ProductTypeDto;
import com.example.ZVnMobile.entities.ProductColorEntity;
import com.example.ZVnMobile.entities.ProductEntity;
import com.example.ZVnMobile.entities.ProductTypeEntity;

@Component
public class ProductTypeConverter {
	
	@Autowired
	private ProductColorConverter typeColorConverter;
	
	@Autowired
	private ProductColorConverter colorConverter;

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
	public ProductTypeEntity productTypeDtoToProductTypeEntity(ProductEntity productEntity, ProductTypeDto typeDto) {
		ProductTypeEntity typeEntity = new ProductTypeEntity();
		typeEntity.setRam(typeDto.getRam());
		typeEntity.setRoom(typeDto.getRoom());
		typeEntity.setBasePrice(typeDto.getBasePrice());
		typeEntity.setPrice(typeDto.getPrice());
		typeEntity.setDiscount(typeDto.getDiscount());
		typeEntity.setProductEntityInType(productEntity);
		typeEntity.setProductEntityInType(productEntity);
		
		List<ProductColorEntity> listColor = new ArrayList<>();
		for(ProductColorDto colorDto : typeDto.getListTypeColor()) {
			ProductColorEntity colorEntity = colorConverter.productColorDtoToProductColorEntity(typeEntity, colorDto);
			listColor.add(colorEntity);
		}
		typeEntity.setListTypeColorEntities(listColor);
		return typeEntity;
	}
}
