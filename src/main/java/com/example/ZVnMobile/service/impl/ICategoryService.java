package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.dto.CategoryDto;
import com.example.ZVnMobile.payload.DataResponse;

public interface ICategoryService {
	DataResponse getAllCategory();
	DataResponse getCategoryByName(String categoryName);
	DataResponse getCategoryById(Long id);
	DataResponse insertCategory(CategoryDto categoryDto);
	DataResponse updateCategory(CategoryDto categoryDto);
}
