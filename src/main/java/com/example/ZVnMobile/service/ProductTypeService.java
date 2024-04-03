package com.example.ZVnMobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.dto.ProductColorDto;
import com.example.ZVnMobile.dto.ProductTypeDto;
import com.example.ZVnMobile.entities.ProductEntity;
import com.example.ZVnMobile.entities.ProductTypeEntity;
import com.example.ZVnMobile.repository.ProductTypeRepository;
import com.example.ZVnMobile.service.impl.IProductColorService;
import com.example.ZVnMobile.service.impl.IProductTypeService;

@Service
public class ProductTypeService implements IProductTypeService{
	
	@Autowired
	private ProductTypeRepository typeRepository;
	
	@Autowired
	private IProductColorService colorService;

	@Override
	public boolean insertProductType(ProductEntity productEntity, ProductTypeDto typeDto) {
		boolean isSuccess = false;
		try {
			ProductTypeEntity typeEntity = new ProductTypeEntity();
			typeEntity.setRam(typeDto.getRam());
			typeEntity.setRoom(typeDto.getRoom());
			typeEntity.setBasePrice(typeDto.getBasePrice());
			typeEntity.setPrice(typeDto.getPrice());
			typeEntity.setDiscount(typeDto.getDiscount());
			typeEntity.setProductEntityInType(productEntity);
			typeEntity = typeRepository.save(typeEntity);
			
			boolean isInsertColor = true;
			for(ProductColorDto colorDto : typeDto.getListTypeColor()) {
				isInsertColor = colorService.insertColorOnType(typeEntity, colorDto);
				if(isInsertColor==false) {
					break;
				}
			}
			
			if(typeEntity.getId()!=null && isInsertColor==true) {
				isSuccess = true;
			}
		} catch (Exception e) {
			isSuccess = false;
			System.out.println("Error: " + e.getMessage());
		}
		return isSuccess;
	}

}
