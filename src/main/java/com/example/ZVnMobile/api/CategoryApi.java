package com.example.ZVnMobile.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.service.impl.ICategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/category")
public class CategoryApi {
	
	@Autowired
	private ICategoryService iCategoryService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllCategory(){
		return new ResponseEntity<>(iCategoryService.getAllCategory(), HttpStatus.OK);
	}
	
	@GetMapping("/getByName/{categoryName}")
	public ResponseEntity<?> getCategoryByName(
			@PathVariable("categoryName")String categoryName){
		return new ResponseEntity<>(iCategoryService.getCategoryByName(categoryName), HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getCategoryById(
			@PathVariable("id") Long id){
		
		return new ResponseEntity<>(iCategoryService.getCategoryById(id), HttpStatus.OK);
	}
}
