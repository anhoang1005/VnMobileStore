package com.example.ZVnMobile.convert;

import org.springframework.stereotype.Component;

import com.example.ZVnMobile.dto.SupplierDto;
import com.example.ZVnMobile.entities.SupplierEntity;

@Component
public class SupplierConverter {
	public SupplierDto entityToDto(SupplierEntity entity) {
		SupplierDto dto = new SupplierDto();
		dto.setId(entity.getId());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setSupplierName(entity.getSupplierName());
		dto.setEmail(entity.getEmail());
		dto.setDescription(entity.getDescription());
		dto.setAdress(entity.getAdress());
		return dto;
	}
}
