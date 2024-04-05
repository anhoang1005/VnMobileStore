package com.example.ZVnMobile.api;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.service.VNPayService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/payment")
public class VnPayApi {
	
	@Autowired
	private VNPayService vnpayService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createPayment() throws UnsupportedEncodingException{
		return new ResponseEntity<>(vnpayService.createPayment(1000000000, "Thanh toan don hang!"), HttpStatus.OK);
	}
}