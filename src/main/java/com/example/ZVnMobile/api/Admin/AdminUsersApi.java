package com.example.ZVnMobile.api.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.service.impl.IUsersService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/user")
public class AdminUsersApi {
	
	@Autowired
	private IUsersService iUsersService;
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllUsers(){
		return new ResponseEntity<>(iUsersService.getAllUsers(), HttpStatus.OK);
	}
	
	@PostMapping("/updateRole")
	public ResponseEntity<?> updateUsers(
			@RequestParam("id") Long id,
			@RequestParam("updateRole")String role){
		return new ResponseEntity<>(iUsersService.getAllUsers(), HttpStatus.OK);
	}
	
	@PostMapping("/lockStatus")
	public ResponseEntity<?> lockOrUnlockUsers(
			@RequestParam("id") Long id,
			@RequestParam("deleted") boolean lock){
		return new ResponseEntity<>(iUsersService.getAllUsers(), HttpStatus.OK);
	}
}
