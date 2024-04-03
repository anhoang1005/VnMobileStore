package com.example.ZVnMobile.api.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.service.impl.IUsersService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/customer/user")
public class CustomerUsersApi {
	
	@Autowired
	private IUsersService iUsersService;
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<?> changePassword(
			@RequestParam("email")String email,
			@RequestParam("password")String password,
			@RequestParam("newPassword")String newPassword){
		return new ResponseEntity<>(iUsersService.changePassword(email, password, newPassword), HttpStatus.OK);
	}
	
}
