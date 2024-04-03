package com.example.ZVnMobile.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.service.impl.IProductReviewService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/review")
public class ProductReviewApi {
	
	@Autowired
	private IProductReviewService iproductReviewService;

	@GetMapping("/test")
	public ResponseEntity<?> test(){
		return new ResponseEntity<>("test", HttpStatus.OK);
	}
	
	@GetMapping("/getbyPid/{id}/{pageNumber}")
	public ResponseEntity<?> getReviewByProductId(
			@PathVariable("id")Long productId,
			@PathVariable("pageNumber")int pageNumber){
		return new ResponseEntity<>(iproductReviewService.getReviewByProductId(productId, pageNumber), HttpStatus.OK);
	}
	
	@GetMapping("/getbySlug/{productSlug}/{pageNumber}")
	public ResponseEntity<?> getReviewByProductSlug(
			@PathVariable("productSlug")String productaSlug,
			@PathVariable("pageNumber")int pageNumber){
		return new ResponseEntity<>(iproductReviewService.getReviewByProductSlug(productaSlug, pageNumber), HttpStatus.OK);
	}
	
}
