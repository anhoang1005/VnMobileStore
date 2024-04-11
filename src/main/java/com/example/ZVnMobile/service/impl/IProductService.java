package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.dto.ProductDto;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.InsertProductRequest;

public interface IProductService {
	DataResponse getAllProductCard(int pageNumber);
	DataResponse getProductByProductSlug(String productSlug);
	DataResponse getproductById(Long id);
	DataResponse insertProduct(InsertProductRequest insertProductRequest);
	DataResponse updateProduct(ProductDto productDto);
	DataResponse deleteProduct(Long id);
	DataResponse getProductDetail(String productSlug);
	DataResponse getProductBySupplier(Long id, int pageNumber);
}
