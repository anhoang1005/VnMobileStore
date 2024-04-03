package com.example.ZVnMobile.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.service.impl.IProductInfoService;
import com.example.ZVnMobile.service.impl.IProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductApi {
	
	@Autowired
	private IProductService iProductService;
	
	@Autowired
	private IProductInfoService iProductInfoService;

	@GetMapping("/getall/{pageNumber}")
	public ResponseEntity<?> getAllProductCard(
			@PathVariable("pageNumber")int pageNumber){
		return new ResponseEntity<>(iProductService.getAllProductCard(pageNumber), HttpStatus.OK);
	}
	
	@GetMapping("/getBySlug/{slug}")
	public ResponseEntity<?> getProductBySlug(
			@PathVariable("slug")String productSlug){
		return new ResponseEntity<>(iProductService.getProductByProductSlug(productSlug), HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getProductById(
			@PathVariable("id")Long id){
		return new ResponseEntity<>(iProductService.getproductById(id), HttpStatus.OK);
	}
	
	@GetMapping("/info/{id}")
	public ResponseEntity<?> getInfoByProductId(
			@PathVariable("id")Long id){
		
		return new ResponseEntity<>(iProductInfoService.getByProductId(id), HttpStatus.OK);
	}
	
	@GetMapping("/detail/{slug}")
	public ResponseEntity<?> getProductDetail(
			@PathVariable("slug")String productSlug){
		return new ResponseEntity<>(iProductService.getProductDetail(productSlug), HttpStatus.OK);
	}
}
