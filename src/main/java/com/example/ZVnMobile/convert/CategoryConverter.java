package com.example.ZVnMobile.convert;

import org.springframework.stereotype.Component;

import com.example.ZVnMobile.dto.CategoryDto;
import com.example.ZVnMobile.entities.CategoryEntity;

@Component
public class CategoryConverter {
	public CategoryDto entityToDto(CategoryEntity categoryEntity) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(categoryEntity.getId());
		categoryDto.setCategoryName(categoryEntity.getCategoryName());
		categoryDto.setDesciption(categoryEntity.getDescription());
		return categoryDto;
	}
}
