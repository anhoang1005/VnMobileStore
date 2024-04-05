package com.example.ZVnMobile.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.convert.ProductConvertter;
import com.example.ZVnMobile.dto.ProductCardDto;
import com.example.ZVnMobile.dto.ProductDetailDto;
import com.example.ZVnMobile.dto.ProductDto;
import com.example.ZVnMobile.dto.ProductTypeDto;
import com.example.ZVnMobile.entities.CategoryEntity;
import com.example.ZVnMobile.entities.ProductEntity;
import com.example.ZVnMobile.entities.SupplierEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.InsertProductRequest;
import com.example.ZVnMobile.repository.CategoryRepositry;
import com.example.ZVnMobile.repository.ProductRepository;
import com.example.ZVnMobile.repository.SupplierRepository;
import com.example.ZVnMobile.service.impl.IProductInfoService;
import com.example.ZVnMobile.service.impl.IProductService;
import com.example.ZVnMobile.service.impl.IProductTypeService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductConvertter productConvertter;

	@Autowired
	private CategoryRepositry categoryRepositry;

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private IProductInfoService infoService;
	
	@Autowired IProductTypeService typeService;

	@Override
	public DataResponse getAllProductCard(int pageNumber) {
		DataResponse dataResponse = new DataResponse();
		try {
			Pageable pageable = PageRequest.of(pageNumber - 1, 15);
			Page<ProductEntity> listProductEntities = productRepository.findAll(pageable);
			List<ProductCardDto> listCardDtos = new ArrayList<>();
			for (ProductEntity entity : listProductEntities) {
				ProductCardDto productCardDto = productConvertter.entityToProductCardDto(entity);
				listCardDtos.add(productCardDto);
			}
			dataResponse.setData(listCardDtos);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setMessage("Loi: " + e.getMessage());
			dataResponse.setSuccess(false);
		}

		return dataResponse;
	}

	@Override
	public DataResponse getProductByProductSlug(String productSlug) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductEntity entity = productRepository.findOneByProductSlug(productSlug);
			ProductCardDto productCardDto = productConvertter.entityToProductCardDto(entity);
			dataResponse.setData(productCardDto);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		return dataResponse;
	}

	@Override
	public DataResponse getproductById(Long id) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductEntity entity = productRepository.findOneById(id);
			dataResponse.setData(productConvertter.entityToProductCardDto(entity));
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		return dataResponse;
	}

	@Override
	public DataResponse insertProduct(InsertProductRequest insertProductRequest) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductEntity productEntity = new ProductEntity();
			productEntity.setTitle(insertProductRequest.getTitle());

			String titleToProductSlug = insertProductRequest.getTitle().toLowerCase().replaceAll("\\s+", "-");
			productEntity.setProductSlug(titleToProductSlug);

			productEntity.setThumbnail(insertProductRequest.getThumbnail());
			productEntity.setPrice(insertProductRequest.getPrice());
			productEntity.setDiscount(insertProductRequest.getDiscount());
			productEntity.setDescription(insertProductRequest.getDescription());
			productEntity.setCreatedAt(new Date());
			productEntity.setDeleted(true);

			CategoryEntity categoryEntity = categoryRepositry.findOneById(insertProductRequest.getCategoryId());
			SupplierEntity supplierEntity = supplierRepository.findOneById(insertProductRequest.getSupplierId());
			productEntity.setCategoryEntityInProduct(categoryEntity);
			productEntity.setSupplierEntityInProduct(supplierEntity);
			productEntity = productRepository.save(productEntity);
			
			boolean isInsertInfo = infoService.insertProductInfo(productEntity, insertProductRequest.getProductInfo());
			boolean isInsertListType = true;
			for(ProductTypeDto typeDto : insertProductRequest.getListProdductType()) {
				boolean isInsertType = typeService.insertProductType(productEntity, typeDto);
				if(isInsertType == false) {
					isInsertListType = false;
					break;
				}
			}
			
			if (productEntity.getId() != null && isInsertInfo==true && isInsertListType == true) {
				dataResponse.setData("Thêm sản phẩm " + productEntity.getId() + " thanh cong!");
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		return dataResponse;
	}

	@Override
	public DataResponse updateProduct(ProductDto productDto) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductEntity entity = productRepository.findOneById(productDto.getId());
			entity = productConvertter.productDtoToProductEntity(productDto);
			entity.setId(productDto.getId());
			entity = productRepository.save(entity);
			dataResponse.setData("Thanh cong!");
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}

		return dataResponse;
	}

	@Override
	public DataResponse deleteProduct(Long id) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductEntity entity = productRepository.findOneById(id);
			entity.setDeleted(false);
			entity = productRepository.save(entity);
			dataResponse.setSuccess(true);
			dataResponse.setData("OK");
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		return dataResponse;
	}

	@Override
	public DataResponse getProductDetail(String productSlug) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductEntity productEntity = productRepository.findOneByProductSlug(productSlug);
			ProductDetailDto productDetailDto = productConvertter.entityToProductDetailDto(productEntity);
			dataResponse.setData(productDetailDto);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}

		return dataResponse;
	}

}