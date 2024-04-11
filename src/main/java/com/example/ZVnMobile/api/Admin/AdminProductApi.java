package com.example.ZVnMobile.api.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.dto.ProductDto;
import com.example.ZVnMobile.payload.request.InsertProductRequest;
import com.example.ZVnMobile.service.impl.IProductInfoService;
import com.example.ZVnMobile.service.impl.IProductService;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/product")
public class AdminProductApi {

	@Autowired
	private IProductService iProductService;
	
	@Autowired
	private IProductInfoService iProductInfoService;
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertProduct(@RequestBody InsertProductRequest insertProductRequest){
		return new ResponseEntity<>(iProductService.insertProduct(insertProductRequest), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto){
		return new ResponseEntity<>(iProductService.updateProduct(productDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> updateProduct(@RequestParam("id") Long productId){
		return new ResponseEntity<>(iProductService.deleteProduct(productId), HttpStatus.OK);
	}
	
	@GetMapping("/info/{id}")
	public ResponseEntity<?> getInfoByProductId(
			@PathVariable("id")Long id){
		
		return new ResponseEntity<>(iProductInfoService.getByProductId(id), HttpStatus.OK);
	}
	
	@GetMapping("/getbysupplier/{id}/{pageNumber}")
	public ResponseEntity<?> getProductBySupplier(
			@PathVariable("id")Long id,
			@PathVariable("pageNumber")int pageNumber){
		return new ResponseEntity<>(iProductService.getProductBySupplier(id, pageNumber), HttpStatus.OK);
	}
}
