package com.example.ZVnMobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.dto.ProductColorDto;
import com.example.ZVnMobile.entities.ProductColorEntity;
import com.example.ZVnMobile.entities.ProductTypeEntity;
import com.example.ZVnMobile.repository.ProductColorRepository;
import com.example.ZVnMobile.service.impl.IProductColorService;

@Service
public class ProductColorService implements IProductColorService{
	
	@Autowired
	private ProductColorRepository colorRepository;

	@Override
	public boolean insertColorOnType(ProductTypeEntity typeEntity, ProductColorDto colorDto) {
		boolean isSuccess = false;
		try {
			ProductColorEntity colorEntity = new ProductColorEntity();
			colorEntity.setColor(colorDto.getColor());
			colorEntity.setSoldQuantity(colorDto.getSoldQuantity());
			colorEntity.setInventoryQuantity(colorDto.getInventoryQuantity());
			colorEntity.setProductTypeEntityInColor(typeEntity);
			
			colorEntity = colorRepository.save(colorEntity);
			if(colorEntity.getId()!=null) {
				isSuccess = true;
			}
		} catch (Exception e) {
			isSuccess = false;
			System.out.println("Error: " + e.getMessage());
		}
		
		return isSuccess;
	}

}
