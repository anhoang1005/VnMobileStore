package com.example.ZVnMobile.convert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ZVnMobile.dto.ProductCardDto;
import com.example.ZVnMobile.dto.ProductDetailDto;
import com.example.ZVnMobile.dto.ProductDto;
import com.example.ZVnMobile.dto.ProductOfSupplierDto;
import com.example.ZVnMobile.dto.ProductReviewDto;
import com.example.ZVnMobile.dto.ProductTypeDto;
import com.example.ZVnMobile.entities.ProductColorEntity;
import com.example.ZVnMobile.entities.ProductEntity;
import com.example.ZVnMobile.entities.ProductReviewEntity;
import com.example.ZVnMobile.entities.ProductTypeEntity;
import com.example.ZVnMobile.repository.CategoryRepositry;
import com.example.ZVnMobile.repository.SupplierRepository;

@Component
public class ProductConvertter {

	@Autowired
	private CategoryRepositry categoryRepositry;

	@Autowired
	private SupplierRepository supplerRepository;

//	@Autowired
//	private ProductInfoConverter productInfoConverter;

	@Autowired
	private ProductReviewConverter productReviewConverter;

	@Autowired
	private ProductTypeConverter productTypeConverter;

	public ProductCardDto entityToProductCardDto(ProductEntity entity) {
		ProductCardDto dto = new ProductCardDto();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setThumbnail(entity.getThumbnail());
		dto.setPrice(entity.getPrice());
		dto.setDiscount(entity.getDiscount());
		dto.setProductSlug(entity.getProductSlug());

		Integer sumRateStar = 0;
		for (ProductReviewEntity reviewEntity : entity.getListProductReviewEntities()) {
			sumRateStar += reviewEntity.getRatingStar();
		}
		Double ratingStar = 1.0 * sumRateStar / entity.getListProductReviewEntities().size();
		if (entity.getListProductReviewEntities().size() == 0) {
			ratingStar = 5.0;
		}
		dto.setRatingStar(ratingStar);

		// ProductInfoDto productInfoDto =
		// productInfoConverter.entityToProductInfoDto(entity.getProductInfoEntityInProduct());
		dto.setProductInfo(entity.getProductInfoEntityInProduct());
		// dto.setProductInfo(productInfoDto);

		List<ProductTypeDto> listType = new ArrayList<>();
		for (ProductTypeEntity typeEntity : entity.getListProductTypeEntities()) {
			ProductTypeDto typeDto = productTypeConverter.productTypeEntityToDto(typeEntity);
			typeDto.setListTypeColor(null);
			listType.add(typeDto);
		}
		dto.setListType(listType);

		return dto;
	}

	public ProductEntity productDtoToProductEntity(ProductDto dto) {
		ProductEntity entity = new ProductEntity();
		entity.setTitle(dto.getTitle());
		entity.setThumbnail(dto.getThumbnail());
		entity.setProductSlug(dto.getProductSlug());
		entity.setPrice(dto.getPrice());
		entity.setDiscount(dto.getDiscount());
		entity.setDescription(dto.getDescription());
		entity.setCreatedAt(new Date());
		entity.setDeleted(true);

		entity.setSupplierEntityInProduct(supplerRepository.findOneById(dto.getSupplierId()));
		entity.setCategoryEntityInProduct(categoryRepositry.findOneById(dto.getCategoryId()));
		return entity;
	}

	public ProductDetailDto entityToProductDetailDto(ProductEntity entity) {
		ProductDetailDto dto = new ProductDetailDto();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setThumbnail(entity.getThumbnail());
		dto.setPrice(entity.getPrice());
		dto.setDiscount(entity.getDiscount());
		dto.setProductSlug(entity.getProductSlug());
		dto.setDescription(entity.getDescription());

		dto.setProductInfo(entity.getProductInfoEntityInProduct());
		dto.setListThumbnail(entity.getListProductThumbnailEntities());

		Integer sumRateStar = 0;
		List<ProductReviewDto> listReview = new ArrayList<>();
		for (ProductReviewEntity reviewEntity : entity.getListProductReviewEntities()) {
			ProductReviewDto reviewDto = productReviewConverter.productReviewEntityToProductReviewDto(reviewEntity);
			sumRateStar += reviewEntity.getRatingStar();
			listReview.add(reviewDto);
		}
		Double ratingStar = 1.0 * sumRateStar / entity.getListProductReviewEntities().size();
		if (entity.getListProductReviewEntities().size() == 0) {
			ratingStar = 5.0;
		}
		dto.setRatingStar(ratingStar);
		dto.setListReview(listReview.size());

		List<ProductTypeDto> listTypeDto = new ArrayList<>();
		for (ProductTypeEntity typeEntity : entity.getListProductTypeEntities()) {
			ProductTypeDto typeDto = productTypeConverter.productTypeEntityToDto(typeEntity);
			listTypeDto.add(typeDto);
		}
		dto.setListType(listTypeDto);
		return dto;
	}

	public ProductOfSupplierDto entityToProductSupplierDto(ProductEntity entity) {
		ProductOfSupplierDto dto = new ProductOfSupplierDto();
		dto.setId(entity.getId());
		dto.setThumbnail(entity.getThumbnail());
		dto.setTitle(entity.getTitle());
		dto.setCategory(entity.getCategoryEntityInProduct().getCategoryName());
		dto.setVersionQuantity(entity.getListProductTypeEntities().size());

		Integer sumRateStar = 0;
		for (ProductReviewEntity reviewEntity : entity.getListProductReviewEntities()) {
			sumRateStar += reviewEntity.getRatingStar();
		}
		Double ratingStar = 1.0 * sumRateStar / entity.getListProductReviewEntities().size();
		if (entity.getListProductReviewEntities().size() == 0) {
			ratingStar = 0.0;
		}
		dto.setRateStar(ratingStar);
		
		int soldQuantity = 0;
		for (ProductTypeEntity typeEntity : entity.getListProductTypeEntities()) {
			for (ProductColorEntity colorEntity : typeEntity.getListTypeColorEntities()) {
				soldQuantity += colorEntity.getSoldQuantity();
			}
		}
		dto.setQuantitySold(soldQuantity);
		return dto;
	}
}
