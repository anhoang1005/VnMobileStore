package com.example.ZVnMobile.service.impl;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.ZVnMobile.dto.ProductDto;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.InsertProductRequest;

public interface IProductService {
	DataResponse getAllProductCard(int pageNumber);
	DataResponse getProductByProductSlug(String productSlug);
	DataResponse getproductById(Long id);
	DataResponse insertProduct(InsertProductRequest insertProductRequest);
	DataResponse insertProductWithThumbnail(MultipartFile file, List<MultipartFile> listFile,InsertProductRequest insertProductRequest);
	DataResponse updateProduct(ProductDto productDto);
	DataResponse deleteProduct(Long id);
	DataResponse getProductDetail(String productSlug);
	DataResponse getProductBySupplier(Long id, int pageNumber);
	DataResponse getProductDashboard(int pageNumber);
	
	DataResponse getProductAdminDashboard(Long cateId, Long supplierId, Boolean status, int sort, int pageNumber);
	DataResponse getProductByTitleLike(String title, int PageNumber);
	DataResponse getProductByIdDashBoard(Long id);
	
	DataResponse lockOrUnlockProduct(Long id, boolean deleted);
}
