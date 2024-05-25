package com.example.ZVnMobile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ZVnMobile.convert.ProductConvertter;
import com.example.ZVnMobile.convert.ProductInfoConverter;
import com.example.ZVnMobile.convert.ProductTypeConverter;
import com.example.ZVnMobile.dto.ProductCardDto;
import com.example.ZVnMobile.dto.ProductDashBoardDto;
import com.example.ZVnMobile.dto.ProductDetailDto;
import com.example.ZVnMobile.dto.ProductDto;
import com.example.ZVnMobile.dto.ProductOfSupplierDto;
import com.example.ZVnMobile.dto.ProductTypeDto;
import com.example.ZVnMobile.entities.CategoryEntity;
import com.example.ZVnMobile.entities.ProductEntity;
import com.example.ZVnMobile.entities.ProductInfoEntity;
import com.example.ZVnMobile.entities.ProductThumbnailEntity;
import com.example.ZVnMobile.entities.ProductTypeEntity;
import com.example.ZVnMobile.entities.SupplierEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.InsertProductRequest;
import com.example.ZVnMobile.repository.CategoryRepositry;
import com.example.ZVnMobile.repository.ProductRepository;
import com.example.ZVnMobile.repository.SupplierRepository;
import com.example.ZVnMobile.service.impl.IProductService;
import com.example.ZVnMobile.utils.PageUtils;

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
	private CloudinaryService cloudinaryService;

	@Autowired
	private ProductInfoConverter infoConverter;

	@Autowired
	private ProductTypeConverter typeConverter;

	@Autowired
	private PageUtils pageUtils;

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
			dataResponse.setSuccess(false);
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
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
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
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
			dataResponse.setMessage("OK");
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
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
			ProductEntity checkExistProduct = productRepository.findOneByProductSlug(titleToProductSlug);
			if (checkExistProduct != null) {
				dataResponse.setMessage("Sản phẩm đã tồn tại!");
				dataResponse.setSuccess(false);
				return dataResponse;
			} else {
				productEntity.setProductSlug(titleToProductSlug);
			}

			productEntity.setThumbnail(insertProductRequest.getThumbnail());
			productEntity.setPrice(insertProductRequest.getPrice());
			productEntity.setDiscount(insertProductRequest.getDiscount());
			productEntity.setDescription(insertProductRequest.getDescription());
			productEntity.setDeleted(true);

			CategoryEntity categoryEntity = categoryRepositry.findOneById(insertProductRequest.getCategoryId());
			SupplierEntity supplierEntity = supplierRepository.findOneById(insertProductRequest.getSupplierId());
			productEntity.setCategoryEntityInProduct(categoryEntity);
			productEntity.setSupplierEntityInProduct(supplierEntity);

			ProductInfoEntity infoEntity = infoConverter.productInfoDtoToProductInfoEntity(productEntity,
					insertProductRequest.getProductInfo());
			productEntity.setProductInfoEntityInProduct(infoEntity);

			List<ProductTypeEntity> listTypeEntities = new ArrayList<>();
			for (ProductTypeDto typeDto : insertProductRequest.getListProdductType()) {
				ProductTypeEntity typeEntity = typeConverter.productTypeDtoToProductTypeEntity(productEntity, typeDto);
				listTypeEntities.add(typeEntity);
			}
			productEntity.setListProductTypeEntities(listTypeEntities);

			productEntity = productRepository.save(productEntity);
			dataResponse.setSuccess(true);
			dataResponse.setMessage("Them san pham " + productEntity.getId() + " thanh cong");
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
		}
		return dataResponse;
	}

	@Override
	public DataResponse insertProductWithThumbnail(MultipartFile file, List<MultipartFile> listFile,
			InsertProductRequest insertProductRequest) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductEntity productEntity = new ProductEntity();
			productEntity.setTitle(insertProductRequest.getTitle());

			String titleToProductSlug = insertProductRequest.getTitle().toLowerCase().replaceAll("\\s+", "-");
			ProductEntity checkExistProduct = productRepository.findOneByProductSlug(titleToProductSlug);
			if (checkExistProduct != null) {
				dataResponse.setMessage("Sản phẩm đã tồn tại!");
				dataResponse.setSuccess(false);
				return dataResponse;
			} else {
				productEntity.setProductSlug(titleToProductSlug);
			}
			DataResponse response = cloudinaryService.upload(file);
			if (response.isSuccess() == true) {
				productEntity.setThumbnail((String) response.getData());
			} else {
				dataResponse.setMessage("Lỗi upload ảnh!");
				dataResponse.setSuccess(false);
				return dataResponse;
			}
			productEntity.setPrice(insertProductRequest.getPrice());
			productEntity.setDiscount(insertProductRequest.getDiscount());
			productEntity.setDescription(insertProductRequest.getDescription());
			productEntity.setDeleted(true);

			CategoryEntity categoryEntity = categoryRepositry.findOneById(insertProductRequest.getCategoryId());
			SupplierEntity supplierEntity = supplierRepository.findOneById(insertProductRequest.getSupplierId());
			productEntity.setCategoryEntityInProduct(categoryEntity);
			productEntity.setSupplierEntityInProduct(supplierEntity);

			ProductInfoEntity infoEntity = infoConverter.productInfoDtoToProductInfoEntity(productEntity,
					insertProductRequest.getProductInfo());
			productEntity.setProductInfoEntityInProduct(infoEntity);

			List<ProductTypeEntity> listTypeEntities = new ArrayList<>();
			for (ProductTypeDto typeDto : insertProductRequest.getListProdductType()) {
				ProductTypeEntity typeEntity = typeConverter.productTypeDtoToProductTypeEntity(productEntity, typeDto);
				listTypeEntities.add(typeEntity);
			}
			productEntity.setListProductTypeEntities(listTypeEntities);
			System.out.println(listFile.size());
			System.out.println(listFile);
			List<ProductThumbnailEntity> listThumbnailEntities = new ArrayList<>();
			for (MultipartFile image : listFile) {
				DataResponse urlImage = cloudinaryService.upload(image);
				if (urlImage.isSuccess()) {
					ProductThumbnailEntity entity = new ProductThumbnailEntity();
					entity.setThumbnail((String) response.getData());
					entity.setProductEntityInThumbnail(productEntity);
					listThumbnailEntities.add(entity);
				}
			}
			productEntity.setListProductThumbnailEntities(listThumbnailEntities);

			productEntity = productRepository.save(productEntity);
			dataResponse.setSuccess(true);
			dataResponse.setMessage("Them san pham " + productEntity.getId() + " thanh cong");
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
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
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
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
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
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
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
		}

		return dataResponse;
	}

	@Override
	public DataResponse getProductBySupplier(Long id, int pageNumber) {
		DataResponse dataResponse = new DataResponse();
		try {
			Pageable pageable = PageRequest.of(pageNumber - 1, 15);

			SupplierEntity supplierEntity = supplierRepository.findOneById(id);
			Page<ProductEntity> listProduct = productRepository.findBySupplierEntityInProduct(supplierEntity, pageable);
			List<ProductOfSupplierDto> listDtos = new ArrayList<>();
			for (ProductEntity entity : listProduct) {
				ProductOfSupplierDto dto = productConvertter.entityToProductSupplierDto(entity);
				listDtos.add(dto);
			}
			dataResponse.setData(listDtos);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
		}
		return dataResponse;
	}

	@Override
	public DataResponse getProductDashboard(int pageNumber) {
		DataResponse dataResponse = new DataResponse();
		Pageable pageable = PageRequest.of(pageNumber - 1, 15);
		try {
			Page<ProductEntity> listEntities = productRepository.findAll(pageable);
			List<ProductDashBoardDto> listDtos = new ArrayList<>();
			for (ProductEntity entity : listEntities) {
				ProductDashBoardDto dto = productConvertter.entityToProductDashBoardDto(entity);
				listDtos.add(dto);
			}
			dataResponse.setData(listDtos);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
		}
		return dataResponse;
	}

	@Override
	public DataResponse lockOrUnlockProduct(Long id, boolean deleted) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductEntity productEntity = productRepository.findOneById(id);
			if (productEntity != null) {
				productEntity.setDeleted(deleted);
				productEntity = productRepository.save(productEntity);
				dataResponse.setData("OK");
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
		}
		return dataResponse;
	}

	@Override
	public DataResponse getProductAdminDashboard(Long cateId, Long supplierId, Boolean status, int sort,
			int pageNumber) {
		DataResponse dataResponse = new DataResponse();
		if (cateId == 0) {
			cateId = null;
		}
		if (supplierId == 0) {
			supplierId = null;
		}
		try {
			Sort sortFinal = Sort.by(Sort.Order.desc("id"));
			if (sort == 0) {
				sortFinal = Sort.by(Sort.Order.desc("id"));
			} else if (sort == 1) {
				sortFinal = Sort.by(Sort.Order.asc("id"));
			}
			Pageable pageable = PageRequest.of(pageNumber - 1, 15, sortFinal);
			Page<ProductEntity> listPage = productRepository.findByCategoryAndSupplierAndStatusAndSort(status, cateId,
					supplierId, pageable);
			List<ProductDashBoardDto> listDtos = new ArrayList<>();
			for (ProductEntity entity : listPage) {
				ProductDashBoardDto dto = productConvertter.entityToProductDashBoardDto(entity);
				listDtos.add(dto);
			}
			dataResponse.setSuccess(true);
			dataResponse.setData(listDtos);
			dataResponse.setPageData(pageUtils.getPageCount(listPage.getTotalElements(), 15));
			dataResponse.setMessage("OK");
		} catch (Exception e) {
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setMessage("Error");
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse getProductByTitleLike(String title, int pageNumber) {
		DataResponse dataResponse = new DataResponse();
		try {
			Sort sort = Sort.by(Sort.Order.desc("id"));
			Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);
			Page<ProductEntity> listPage = productRepository.findByTitleKeyWord(title, pageable);
			List<ProductDashBoardDto> listDtos = new ArrayList<>();
			for (ProductEntity productEntity : listPage) {
				ProductDashBoardDto dto = productConvertter.entityToProductDashBoardDto(productEntity);
				listDtos.add(dto);
			}
			dataResponse.setData(listDtos);
			dataResponse.setMessage("OK");
			dataResponse.setSuccess(true);
			dataResponse.setPageData(pageUtils.getPageCount(listPage.getTotalElements(), 15));
		} catch (Exception e) {
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Error");
		}
		return dataResponse;
	}

	@Override
	public DataResponse getProductByIdDashBoard(Long id) {
		DataResponse dataResponse = new DataResponse();
		try {
			ProductEntity entity = productRepository.findOneById(id);
			if (entity != null) {
				dataResponse.setData(productConvertter.entityToProductDashBoardDto(entity));
				dataResponse.setMessage("OK");
				dataResponse.setSuccess(true);
			} else {
				dataResponse.setData(null);
				dataResponse.setMessage("OK");
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Error");
		}
		return dataResponse;
	}
}
