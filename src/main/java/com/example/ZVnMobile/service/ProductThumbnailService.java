package com.example.ZVnMobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ZVnMobile.entities.ProductEntity;
import com.example.ZVnMobile.entities.ProductThumbnailEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.repository.ProductThumbnailRepository;
import com.example.ZVnMobile.service.impl.IProductThumbnailService;

@Service
public class ProductThumbnailService implements IProductThumbnailService{
	
	@Autowired
	private ProductThumbnailRepository thumbnailRepository;
	
	@Autowired
	private CloudinaryService cloudinaryService;
	
	@Override
	public boolean insertListThumbnail(ProductEntity product, List<MultipartFile> listFile) {
		boolean isSuccess = false;
		try {
			isSuccess = true;
			for(MultipartFile file : listFile) {
				DataResponse response = cloudinaryService.upload(file);
				if(response.isSuccess()) {
					ProductThumbnailEntity entity = new ProductThumbnailEntity();
					entity.setThumbnail((String)response.getData());
					entity.setProductEntityInThumbnail(product);
					entity = thumbnailRepository.save(entity);
				}
				else {
					isSuccess = false;
					break;
				}
			}
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
		}
		return isSuccess;
	}

}
