package com.example.ZVnMobile.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ZVnMobile.dto.SignupDto;
import com.example.ZVnMobile.service.impl.ILoginService;
import com.example.ZVnMobile.service.impl.IUsersService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UsersApi {
	
	@Autowired
	private ILoginService iLoginService;
	
	@Autowired
	private IUsersService iUsersService;
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> loginUser(
			@RequestParam("username")String username,
			@RequestParam("password")String password){
		return new ResponseEntity<>(iLoginService.loginUser(username, password), HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signupUser(@RequestBody SignupDto signupDto){
		return new ResponseEntity<>(iLoginService.signupUser(signupDto), HttpStatus.OK);
	}
	
	@GetMapping("/verify")
	public ResponseEntity<?> verifyUsers(
			@RequestParam("code") String verifyCode,
			@RequestParam("email") String email){
		return new ResponseEntity<>(iLoginService.verifyUsers(verifyCode, email), HttpStatus.OK);
	}
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestParam("email")String email){
		return new ResponseEntity<>(iUsersService.forgotPassword(email), HttpStatus.OK);
	}
	
	@PostMapping("/verifyPassword")
	public ResponseEntity<?> verifyForgotPassword(
			@RequestParam("email")String email,
			@RequestParam("verifyCode")String verifyCode,
			@RequestParam("newPassword")String newPasswod){
		return new ResponseEntity<>(iUsersService.verifyForgotPassword(email, verifyCode, newPasswod), HttpStatus.OK);
	}
}
