package com.example.ZVnMobile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.convert.CategoryConverter;
import com.example.ZVnMobile.dto.CategoryDto;
import com.example.ZVnMobile.entities.CategoryEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.repository.CategoryRepositry;
import com.example.ZVnMobile.service.impl.ICategoryService;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	private CategoryRepositry categoryRepositry;
	
	@Autowired
	private CategoryConverter categoryConverter;

	@Override
	public DataResponse getAllCategory() {
		DataResponse dataResponse = new DataResponse();
		List<CategoryDto> listDtos = new ArrayList<>();
		try {
			List<CategoryEntity> listEntities = categoryRepositry.findAll();
			for(CategoryEntity entity : listEntities) {
				CategoryDto dto = categoryConverter.entityToDto(entity);
				listDtos.add(dto);
			}
			dataResponse.setSuccess(true);
			dataResponse.setData(listDtos);
			dataResponse.setMessage("get thanh cong");
		} catch (Exception e) {
			dataResponse.setSuccess(true);
			dataResponse.setMessage("Loi: " + e.getMessage());
			System.out.println(e.getMessage());
		}
		return dataResponse;
	}

	@Override
	public DataResponse insertCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResponse updateCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResponse getCategoryByName(String categoryName) {
		DataResponse dataResponse = new DataResponse();
		try {
			CategoryEntity categoryEntity = categoryRepositry.findOneByCategoryName(categoryName);
			CategoryDto categoryDto = categoryConverter.entityToDto(categoryEntity);
			
			dataResponse.setData(categoryDto);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		
		return dataResponse;
	}

	@Override
	public DataResponse getCategoryById(Long id) {
		DataResponse dataResponse = new DataResponse();
		try {
			CategoryEntity categoryEntity = categoryRepositry.findOneById(id);
			CategoryDto categoryDto = categoryConverter.entityToDto(categoryEntity);
			
			dataResponse.setData(categoryDto);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		return dataResponse;
	}
	
}
