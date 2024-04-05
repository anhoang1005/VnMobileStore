package com.example.ZVnMobile.api.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.service.impl.IOrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/customer/order")
public class CustomerOrderApi {
	
	@Autowired
	private IOrderService orderService;

	@PostMapping("/cancelorder")
	public ResponseEntity<?> cancelOrder(
			@RequestParam("orderId") Long orderId,
			@RequestParam("email") String email){
		return new ResponseEntity<>(orderService.cancelOrder(orderId, email), HttpStatus.OK);
	}
}