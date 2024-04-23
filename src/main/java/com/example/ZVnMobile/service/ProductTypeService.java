package com.example.ZVnMobile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.convert.ProductTypeConverter;
import com.example.ZVnMobile.dto.ProductColorDto;
import com.example.ZVnMobile.dto.ProductTypeDto;
import com.example.ZVnMobile.entities.ProductColorEntity;
import com.example.ZVnMobile.entities.ProductEntity;
import com.example.ZVnMobile.entities.ProductTypeEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.repository.ProductRepository;
import com.example.ZVnMobile.repository.ProductTypeRepository;
import com.example.ZVnMobile.service.impl.IProductColorService;
import com.example.ZVnMobile.service.impl.IProductTypeService;

@Service
public class ProductTypeService implements IProductTypeService {

	@Autowired
	private ProductTypeRepository typeRepository;

	@Autowired
	private ProductTypeConverter typeConverter;

	@Autowired
	private IProductColorService colorService;

	@Autowired
	private ProductRepository productRepository;

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
			for (ProductColorDto colorDto : typeDto.getListTypeColor()) {
				isInsertColor = colorService.insertColorOnType(typeEntity, colorDto);
				if (isInsertColor == false) {
					break;
				}
			}

			if (typeEntity.getId() != null && isInsertColor == true) {
				isSuccess = true;
			}
		} catch (Exception e) {
			isSuccess = false;
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public boolean insertListType(ProductEntity productEntity, List<ProductTypeDto> listType) {
		boolean isSuccess = false;
		try {
			boolean check = true;
			for (ProductTypeDto typeDto : listType) {
				ProductTypeEntity typeEntity = new ProductTypeEntity();
				typeEntity.setRam(typeDto.getRam());
				typeEntity.setRoom(typeDto.getRoom());
				typeEntity.setBasePrice(typeDto.getBasePrice());
				typeEntity.setPrice(typeDto.getPrice());
				typeEntity.setDiscount(typeDto.getDiscount());
				typeEntity.setProductEntityInType(productEntity);
				typeEntity = typeRepository.save(typeEntity);

				boolean isInsertColor = true;
				for (ProductColorDto colorDto : typeDto.getListTypeColor()) {
					isInsertColor = colorService.insertColorOnType(typeEntity, colorDto);
					if (isInsertColor == false) {
						break;
					}
				}
				if (typeEntity.getId() != null && isInsertColor == true) {
					isSuccess = true;
				} else {
					check = false;
					break;
				}
			}
			if (check == true) {
				isSuccess = true;
			}
		} catch (Exception e) {
			isSuccess = false;
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		return isSuccess;
	}

	@Override
	public DataResponse getTypeByProductId(Long id) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductEntity productEntity = productRepository.findOneById(id);
			if (productEntity != null) {
				List<ProductTypeDto> listTypeDtos = new ArrayList<>();
				for (ProductTypeEntity typeEntity : productEntity.getListProductTypeEntities()) {
					ProductTypeDto typeDto = typeConverter.productTypeEntityToDto(typeEntity);
					listTypeDtos.add(typeDto);
				}
				dataResponse.setData(listTypeDtos);
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
		}
		return dataResponse;
	}

	@Override
	public boolean insertProductType(ProductTypeDto typeDto) {
		boolean isSuccess = false;
		try {
			ProductTypeEntity entity = new ProductTypeEntity();
			entity.setRam(typeDto.getRam());
			entity.setRoom(typeDto.getRoom());
			entity.setBasePrice(typeDto.getBasePrice());
			entity.setDiscount(typeDto.getDiscount());
			entity.setPrice(typeDto.getPrice());
			entity.setProductEntityInType(null);

		} catch (Exception e) {

		}
		return isSuccess;
	}
}
