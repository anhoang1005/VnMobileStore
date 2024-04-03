package com.example.ZVnMobile.convert;

import org.springframework.stereotype.Component;

import com.example.ZVnMobile.dto.ProductInfoDto;
import com.example.ZVnMobile.entities.ProductInfoEntity;

@Component
public class ProductInfoConverter {

	public ProductInfoDto productInfoEntityToProductInfoDto(ProductInfoEntity entity) {
		ProductInfoDto dto = new ProductInfoDto();
		dto.setBackCamera(entity.getBackCamera());
		dto.setBattery(entity.getBattery());
		dto.setCpu(entity.getCpu());
		dto.setFrontCamera(entity.getFrontCamera());
		dto.setGpu(entity.getGpu());
		dto.setOreraionSystem(entity.getOperatingSystem());
		dto.setProductId(entity.getProductId());
		dto.setScreen(entity.getScreen());
		dto.setWeight(entity.getWeight());
		
		return dto;
	}
}
