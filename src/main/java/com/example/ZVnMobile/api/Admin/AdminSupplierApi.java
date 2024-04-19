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

import com.example.ZVnMobile.dto.SupplierDto;
import com.example.ZVnMobile.payload.request.BillRequest;
import com.example.ZVnMobile.service.impl.ISupplierBillService;
import com.example.ZVnMobile.service.impl.ISupplierService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/supplier")
public class AdminSupplierApi {
	
	@Autowired
	private ISupplierService iSupplierService;
	
	@Autowired
	private ISupplierBillService iSupplierBillService;
	
	@GetMapping
	public ResponseEntity<?> getAllSupplier(){
		return new ResponseEntity<>(iSupplierService.getAllSupplier(), HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getSupplierById(
			@PathVariable("id")Long id){
		return new ResponseEntity<>(iSupplierService.getSupplierById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insertSupplier(
			@RequestBody SupplierDto supplierDto){
		return new ResponseEntity<>(iSupplierService.insertSupplier(supplierDto), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> updateSupplier(
			@RequestBody SupplierDto supplierDto){
		return new ResponseEntity<>(iSupplierService.updateSupplier(supplierDto), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> lockOrUnlockSupplier(
			@RequestParam("id")Long id,
			@RequestParam("deleted")boolean status){
		return new ResponseEntity<>(iSupplierService.lockOrUnlockSupplier(id, status), HttpStatus.OK);
	}
	
	@PostMapping("/createbill")
	public ResponseEntity<?> createBillSupplier(
			@RequestBody BillRequest billRequest){
		return new ResponseEntity<>(iSupplierBillService.createBill(billRequest), HttpStatus.OK);
	}
	
}
