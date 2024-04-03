package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.ProductReviewRequest;

public interface IProductReviewService {

	DataResponse getAllProductReview(int pageNumber, int pageSize);
	DataResponse getReviewByProductId(Long id, int pageNumber);
	DataResponse getReviewByProductSlug(String productSlug, int pageNumber);
	DataResponse insertReview(ProductReviewRequest request);
	DataResponse updateReview(ProductReviewRequest request);
	DataResponse deleteReview(Long id);	
	DataResponse likeReview(Long id);
}
