package com.example.ZVnMobile.convert;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.example.ZVnMobile.dto.ProductReviewDto;
import com.example.ZVnMobile.entities.ProductReviewEntity;

@Component
public class ProductReviewConverter {

	public ProductReviewDto productReviewEntityToProductReviewDto(ProductReviewEntity entity) {
		
		ProductReviewDto dto = new ProductReviewDto();
		dto.setReviewId(entity.getId());
		dto.setUserId(entity.getUsersEntityInReview().getId());
		dto.setFullName(entity.getUsersEntityInReview().getFullName());
		dto.setAvatar(entity.getUsersEntityInReview().getAvatar());
		dto.setProductId(entity.getProductEntityInReview().getId());
		dto.setRateStar(entity.getRatingStar());
		dto.setComment(entity.getComment());
		
		SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		String formattedDate = outputFormat.format(entity.getCreatedAt());
		dto.setCreatedAt(formattedDate);
		dto.setLike(entity.getLike());
		dto.setUsername(entity.getUsersEntityInReview().getEmail());
		return dto;
	}
}
