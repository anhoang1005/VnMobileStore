package com.example.ZVnMobile.api.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.payload.request.ProductReviewRequest;
import com.example.ZVnMobile.service.impl.IProductReviewService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/customer/review")
public class CustomerProductReviewApi {
	
	@Autowired
	private IProductReviewService iproductReviewService;

	@PostMapping("/insert")
	public ResponseEntity<?> insertReview(
			@RequestBody ProductReviewRequest request){
		return new ResponseEntity<>(iproductReviewService.insertReview(request), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateReview(
			@RequestBody ProductReviewRequest request){
		return new ResponseEntity<>(iproductReviewService.updateReview(request), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteReview(
			@RequestParam Long id){
		return new ResponseEntity<>(iproductReviewService.deleteReview(id), HttpStatus.OK);
	}
	
	@GetMapping("/like/{id}")
	public ResponseEntity<?> likeReview(
			@PathVariable("id")Long id){
		return new ResponseEntity<>(iproductReviewService.likeReview(id), HttpStatus.OK);
	}
}
