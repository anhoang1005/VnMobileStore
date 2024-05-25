package com.example.ZVnMobile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.convert.CategoryConverter;
import com.example.ZVnMobile.dto.CategoryDto;
import com.example.ZVnMobile.entities.CategoryEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.repository.CategoryRepositry;
import com.example.ZVnMobile.service.impl.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

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
			long pageCount = 0;
			if (categoryRepositry.count() % 10 == 0) {
				pageCount = categoryRepositry.count() / 10;
			} else {
				pageCount = categoryRepositry.count() / 10 + 1;
			}
			for (CategoryEntity entity : listEntities) {
				CategoryDto dto = categoryConverter.entityToDto(entity);
				listDtos.add(dto);
			}
			dataResponse.setSuccess(true);
			dataResponse.setData(listDtos);
			dataResponse.setPageData(pageCount);
			dataResponse.setMessage("Lấy danh sắch category thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse insertCategory(CategoryDto categoryDto) {
		DataResponse dataResponse = new DataResponse();
		try {
			CategoryEntity categoryEntity = new CategoryEntity();
			categoryEntity.setCategoryName(categoryDto.getCategoryName());
			categoryEntity.setImages(categoryDto.getImages());
			categoryEntity.setDescription(categoryDto.getDesciption());
			categoryEntity.setDeleted(true);

			categoryEntity = categoryRepositry.save(categoryEntity);
			if (categoryEntity != null) {
				dataResponse.setData("OK");
				dataResponse.setSuccess(true);
				dataResponse.setMessage("Them thanh cong");
			}
		} catch (Exception e) {
			e.printStackTrace();
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse updateCategory(CategoryDto categoryDto) {
		DataResponse dataResponse = new DataResponse();
		try {
			CategoryEntity categoryEntity = categoryRepositry.findOneById(categoryDto.getId());
			categoryEntity.setCategoryName(categoryDto.getCategoryName());
			categoryEntity.setImages(categoryDto.getImages());
			categoryEntity.setDescription(categoryDto.getDesciption());
			// categoryEntity.setDeleted(categoryDto.isDeleted());

			categoryEntity = categoryRepositry.save(categoryEntity);
			if (categoryEntity != null) {
				dataResponse.setData("OK");
				dataResponse.setMessage("Update thanh cong");
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
		}
		return dataResponse;
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
			e.printStackTrace();
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
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
			e.printStackTrace();
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse lockOrUnlockCategory(Long id, boolean status) {
		DataResponse dataResponse = new DataResponse();
		try {
			CategoryEntity categoryEntity = categoryRepositry.findOneById(id);
			categoryEntity.setDeleted(status);
			categoryEntity = categoryRepositry.save(categoryEntity);
			if (categoryEntity != null) {
				dataResponse.setData("OK");
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
		}

		return dataResponse;
	}

	@Override
	public DataResponse getPageCategory(int pageNumber) {
		DataResponse dataResponse = new DataResponse();
		try {
			Pageable pageable = PageRequest.of(pageNumber - 1, 10);
			long pageCount = 0;
			if (categoryRepositry.count() % 10 == 0) {
				pageCount = categoryRepositry.count() / 10;
			} else {
				pageCount = categoryRepositry.count() / 10 + 1;
			}
			Page<CategoryEntity> listEntity = categoryRepositry.findAll(pageable);
			List<CategoryDto> listDto = new ArrayList<>();
			for (CategoryEntity categoryEntity : listEntity) {
				CategoryDto categoryDto = new CategoryDto();
				categoryDto = categoryConverter.entityToDto(categoryEntity);
				listDto.add(categoryDto);
			}
			dataResponse.setData(listDto);
			dataResponse.setPageData(pageCount);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse getBySearch(String text) {
		DataResponse dataResponse = new DataResponse();
		try {
			List<CategoryEntity> listSearch = categoryRepositry.findByCategoryNameLike(text);
			dataResponse.setData(listSearch);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse getByDeleted(boolean deleted) {
		DataResponse dataResponse = new DataResponse();
		try {
			List<CategoryEntity> listCategory = categoryRepositry.findByDeleted(deleted);
			List<CategoryDto> listDtos = new ArrayList<>();
			for(CategoryEntity categoryEntity : listCategory) {
				CategoryDto categoryDto = categoryConverter.entityToDto(categoryEntity);
				listDtos.add(categoryDto);
			}
			dataResponse.setData(listDtos);
			dataResponse.setMessage("OK");
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Error");
		}
		return dataResponse;
	}

}
