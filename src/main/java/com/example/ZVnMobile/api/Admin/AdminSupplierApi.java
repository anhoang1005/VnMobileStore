package com.example.ZVnMobile.api.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.service.impl.ISupplierService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/supplier")
public class AdminSupplierApi {
	
	@Autowired
	private ISupplierService iSupplierService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllSupplier(){
		return new ResponseEntity<>(iSupplierService.getAllSupplier(), HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getSupplierById(
			@PathVariable("id")Long id){
		return new ResponseEntity<>(iSupplierService.getSupplierById(id), HttpStatus.OK);
	}
}
