package com.example.ZVnMobile.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.convert.ProductReviewConverter;
import com.example.ZVnMobile.dto.ProductReviewDto;
import com.example.ZVnMobile.entities.ProductEntity;
import com.example.ZVnMobile.entities.ProductReviewEntity;
import com.example.ZVnMobile.entities.UsersEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.ProductReviewRequest;
import com.example.ZVnMobile.repository.ProductRepository;
import com.example.ZVnMobile.repository.ProductReviewRepository;
import com.example.ZVnMobile.repository.UserRepository;
import com.example.ZVnMobile.service.impl.IProductReviewService;

@Service
public class ProductReviewService implements IProductReviewService{
	
	@Autowired
	private ProductReviewRepository productReviewRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductReviewConverter productReviewConverter;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public DataResponse getAllProductReview(int pageNumber, int pageSize) {
		DataResponse dataResponse = new DataResponse();
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		try {
			Page<ProductReviewEntity> listProductReview = productReviewRepository.findAll(pageRequest);
			List<ProductReviewDto> listDto = new ArrayList<>();
			for(ProductReviewEntity entity : listProductReview) {
				ProductReviewDto dto = productReviewConverter.productReviewEntityToProductReviewDto(entity);
				listDto.add(dto);
			}
			dataResponse.setData(listDto);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		return dataResponse;
	}

	@Override
	public DataResponse getReviewByProductId(Long id, int pageNumber) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductEntity productEntity = productRepository.findOneById(id);
			Pageable pageable = PageRequest.of(pageNumber-1, 6);
			List<ProductReviewEntity> listReviewEntity = productReviewRepository.findByProductEntityInReview(productEntity, pageable);
			List<ProductReviewDto> listReviewDtos = new ArrayList<>();
			for(ProductReviewEntity reviewEntity : listReviewEntity) {
				ProductReviewDto reviewDto = productReviewConverter.productReviewEntityToProductReviewDto(reviewEntity);
				listReviewDtos.add(reviewDto);
			}
			dataResponse.setSuccess(true);
			dataResponse.setData(listReviewDtos);
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		return dataResponse;
	}

	@Override
	public DataResponse getReviewByProductSlug(String productSlug, int pageNumber) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductEntity productEntity = productRepository.findOneByProductSlug(productSlug);
			Pageable pageable = PageRequest.of(pageNumber-1, 6);
			List<ProductReviewEntity> listReviewEntity = productReviewRepository.findByProductEntityInReview(productEntity, pageable);
			List<ProductReviewDto> listReviewDtos = new ArrayList<>();
			for(ProductReviewEntity reviewEntity : listReviewEntity) {
				ProductReviewDto reviewDto = productReviewConverter.productReviewEntityToProductReviewDto(reviewEntity);
				listReviewDtos.add(reviewDto);
			}
			dataResponse.setSuccess(true);
			dataResponse.setData(listReviewDtos);
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		return dataResponse;
	}

	@Override
	public DataResponse insertReview(ProductReviewRequest request) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductEntity productEntity = productRepository.findOneByProductSlug(request.getProductSlug());
			UsersEntity usersEntity = userRepository.findByEmail(request.getUsername());
			
			ProductReviewEntity productReviewEntity = new ProductReviewEntity();
			productReviewEntity.setUsersEntityInReview(usersEntity);
			productReviewEntity.setProductEntityInReview(productEntity);
			productReviewEntity.setLike(0);
			productReviewEntity.setComment(request.getComment());
			productReviewEntity.setRatingStar(request.getRatingStar());
			productReviewEntity.setCreatedAt(new Date());
			productReviewEntity = productReviewRepository.save(productReviewEntity);
			
			dataResponse.setSuccess(true);
			dataResponse.setData("OK!");;
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		return dataResponse;
	}

	@Override
	public DataResponse updateReview(ProductReviewRequest request) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductReviewEntity  reviewEntity = productReviewRepository.findOneById(request.getId());
			reviewEntity.setRatingStar(request.getRatingStar());
			reviewEntity.setComment(request.getComment());
			reviewEntity = productReviewRepository.save(reviewEntity);
			
			dataResponse.setSuccess(true);
			dataResponse.setData("OK");
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		return dataResponse;
	}

	@Override
	public DataResponse deleteReview(Long id) {
		DataResponse dataResponse = new DataResponse();
		try {
			productReviewRepository.deleteById(id);
			ProductReviewEntity reviewEntity = productReviewRepository.findOneById(id);
			if(reviewEntity==null) {
				dataResponse.setData("OK");
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		
		return dataResponse;
	}

	@Override
	public DataResponse likeReview(Long id) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductReviewEntity reviewEntity = productReviewRepository.findOneById(id);
			reviewEntity.setLike(reviewEntity.getLike()+1);
			reviewEntity = productReviewRepository.save(reviewEntity);
			dataResponse.setSuccess(true);
			dataResponse.setData(reviewEntity.getLike());
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		return dataResponse;
	}



}
