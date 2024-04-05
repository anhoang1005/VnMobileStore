package com.example.ZVnMobile.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.payload.request.GetFeeRequest;
import com.example.ZVnMobile.payload.request.TimeGHNRequest;
import com.example.ZVnMobile.service.impl.IGHNService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ghn")
public class GHNApi {
	
	@Autowired
	private IGHNService ighnService;
	
	@GetMapping("/getprovince")
	public ResponseEntity<?> getProvince(){
		return new ResponseEntity<>(ighnService.getAllProvince().getData(), HttpStatus.OK);
	}
	
	@GetMapping("/getdistrict/{provinceId}")
	public ResponseEntity<?> getDistrict(
			@PathVariable("provinceId")String provinceId){
		return new ResponseEntity<>(ighnService.getAllDistrict(provinceId).getData(), HttpStatus.OK);
	}
	
	@GetMapping("/getward/{districtId}")
	public ResponseEntity<?> getWard(
			@PathVariable("districtId")String districtId){
		return new ResponseEntity<>(ighnService.getAllWard(districtId).getData(), HttpStatus.OK);
	}
	
	@GetMapping("/getservice/{to_district}")
	public ResponseEntity<?> getService(
			@PathVariable("to_district")Integer to_district){
		return new ResponseEntity<>(ighnService.getService(to_district).getData(), HttpStatus.OK);
	}
	
	@PostMapping("/getFee")
	public ResponseEntity<?> getFee(@RequestBody GetFeeRequest feeRequest){
		return new ResponseEntity<>(ighnService.getFee(feeRequest).getData(), HttpStatus.OK);
	}
	
	@PostMapping("/getTime")
	public ResponseEntity<?> getTime(@RequestBody TimeGHNRequest ghnRequest){
		return new ResponseEntity<>(ighnService.getTime(ghnRequest).getData(), HttpStatus.OK);
	}
}
