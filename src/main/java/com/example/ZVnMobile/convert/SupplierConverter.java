package com.example.ZVnMobile.convert;

import org.springframework.stereotype.Component;

import com.example.ZVnMobile.dto.SupplierBillDto;
import com.example.ZVnMobile.dto.SupplierDto;
import com.example.ZVnMobile.entities.SupplierBillEntity;
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
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setDeleted(entity.isDeleted());
		dto.setProductQuantity(entity.getListProductEntities().size());
		return dto;
	}
	
	public SupplierBillDto billEntityToBillDto(SupplierBillEntity entity) {
		SupplierBillDto  dto = new SupplierBillDto();
		dto.setId(entity.getId());
		dto.setBillTitle(entity.getTitle());
		dto.setSupplierId(entity.getSupplierEntityInBill().getId());
		dto.setSupplierName(entity.getSupplierEntityInBill().getSupplierName());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setTotalPrice(entity.getTotalPrice());
		dto.setDescription(entity.getDescription());
		return dto;
	}
}
