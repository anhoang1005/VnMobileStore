package com.example.ZVnMobile.api.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.dto.CategoryDto;
import com.example.ZVnMobile.service.impl.ICategoryService;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/category")
public class AdminCategoryApi {
	
	@Autowired
	private ICategoryService iCategoryService;
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertCategory(@RequestBody CategoryDto categoryDto){
		return new ResponseEntity<>(iCategoryService.insertCategory(categoryDto), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryDto categoryDto){
		return new ResponseEntity<>(iCategoryService.updateCategory(categoryDto), HttpStatus.OK);
	}
}
