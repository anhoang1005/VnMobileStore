package com.example.ZVnMobile.api.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.dto.CategoryDto;
import com.example.ZVnMobile.service.impl.ICategoryService;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/category")
public class AdminCategoryApi {
	
	@Autowired
	private ICategoryService iCategoryService;
	
	@GetMapping("/getbypage/{pageNumber}")
	public ResponseEntity<?> getPAgeCategory(@PathVariable("pageNumber")int pageNumber){
		return new ResponseEntity<>(iCategoryService.getPageCategory(pageNumber), HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertCategory(@RequestBody CategoryDto categoryDto){
		return new ResponseEntity<>(iCategoryService.insertCategory(categoryDto), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryDto categoryDto){
		return new ResponseEntity<>(iCategoryService.updateCategory(categoryDto), HttpStatus.OK);
	}
	
	@PostMapping("/status")
	public ResponseEntity<?> statusCategory(
			@RequestParam("id") Long id,
			@RequestParam("deleted") boolean status ){
		return new ResponseEntity<>(iCategoryService.lockOrUnlockCategory(id, status), HttpStatus.OK);
	}
}
