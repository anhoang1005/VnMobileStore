package com.example.ZVnMobile.api.Custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ZVnMobile.payload.request.EditProfileRequest;
import com.example.ZVnMobile.service.impl.IUsersService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/custom/user")
public class CustomUserApi {
	
	@Autowired
	private IUsersService iUsersService;
	
	@GetMapping("/getInfo/{email}")
	public ResponseEntity<?> getInfoUser(
			@PathVariable("email") String email){
		return new ResponseEntity<>(iUsersService.getUserInfoByEmail(email), HttpStatus.OK);
	}
	
	@PostMapping("/editProfile")
	public ResponseEntity<?> editProfileUsers(
			@RequestBody EditProfileRequest editProfileRequest){
		return new ResponseEntity<>(iUsersService.editProfile(editProfileRequest), HttpStatus.OK);
	}
	
	@PutMapping("/changeAvatar")
	public ResponseEntity<?> changeAvatarUsers(
			@RequestParam("email") String email,
			@RequestParam("file")MultipartFile file){
		return new ResponseEntity<>(iUsersService.changeAvatar(email, file), HttpStatus.OK);
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<?> changePassword(
			@RequestParam("email")String email,
			@RequestParam("password")String password,
			@RequestParam("newPassword")String newPassword){
		return new ResponseEntity<>(iUsersService.changePassword(email, password, newPassword), HttpStatus.OK);
	}
}
