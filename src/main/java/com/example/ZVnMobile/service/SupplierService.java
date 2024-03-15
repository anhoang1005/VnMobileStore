package com.example.ZVnMobile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.convert.SupplierConverter;
import com.example.ZVnMobile.dto.SupplierDto;
import com.example.ZVnMobile.entities.SupplierEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.repository.SupplerRepository;
import com.example.ZVnMobile.service.impl.ISupplierService;

@Service
public class SupplierService implements ISupplierService{
	
	@Autowired
	private SupplerRepository supplerRepository;
	
	@Autowired
	private SupplierConverter supplierConverter;

	@Override
	public DataResponse getAllSupplier() {
		DataResponse dataResponse = new DataResponse();
		try {
			List<SupplierEntity> listEntities = supplerRepository.findAll();
			List<SupplierDto> listDtos  = new ArrayList<>();
			for(SupplierEntity entity: listEntities) {
				SupplierDto dto = new SupplierDto();
				dto = supplierConverter.entityToDto(entity);
				listDtos.add(dto);
			}
			dataResponse.setData(listDtos);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		
		return dataResponse;
	}

	@Override
	public DataResponse getSupplierById(Long id) {
		DataResponse dataResponse = new DataResponse();
		try {
			SupplierEntity entity = supplerRepository.findOneById(id);
			SupplierDto dto = supplierConverter.entityToDto(entity);
			
			dataResponse.setData(dto);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		return dataResponse;
	}

}
