package com.example.ZVnMobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.convert.ProductInfoConverter;
import com.example.ZVnMobile.dto.ProductInfoDto;
import com.example.ZVnMobile.entities.ProductEntity;
import com.example.ZVnMobile.entities.ProductInfoEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.repository.ProductinfoRepository;
import com.example.ZVnMobile.service.impl.IProductInfoService;

@Service
public class ProductInfoService implements IProductInfoService{
	
	@Autowired
	private ProductinfoRepository productinfoRepository;
	
	@Autowired
	private ProductInfoConverter productInfoConverter;

	@Override
	public DataResponse getByProductId(Long id) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductInfoEntity entity = productinfoRepository.findByProductId(id);
			ProductInfoDto dto = productInfoConverter.productInfoEntityToProductInfoDto(entity);
			dataResponse.setData(dto);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setData("Loi: " + e.getMessage());
			dataResponse.setSuccess(false);
		}
		
		return dataResponse;
	}

	@Override
	public boolean insertProductInfo(ProductEntity productEntity, ProductInfoDto infoDto) {
		boolean isSuccess = false;
		try {
			ProductInfoEntity infoEntity = new ProductInfoEntity();
			infoEntity.setBackCamera(infoDto.getBackCamera());
			infoEntity.setBattery(infoDto.getBattery());
			infoEntity.setCpu(infoDto.getCpu());
			infoEntity.setFrontCamera(infoDto.getFrontCamera());
			infoEntity.setGpu(infoDto.getGpu());
			infoEntity.setOperatingSystem(infoDto.getBackCamera());
			infoEntity.setScreen(infoDto.getScreen());
			infoEntity.setWeight(infoDto.getWeight());
			infoEntity.setProductEntityInInfo(productEntity);
			infoEntity = productinfoRepository.save(infoEntity);
			if(infoEntity!=null) {
				isSuccess = true;
			}
		} catch (Exception e) {
			isSuccess = false;
			System.out.println("Error: " + e.getMessage());
		}
		return isSuccess;
	}
}
