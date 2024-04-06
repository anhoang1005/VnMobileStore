package com.example.ZVnMobile.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ZVnMobile.service.CloudinaryService;

@RestController
@RequestMapping("/api/cloudinary/upload")
public class CloudinaryApi {
	
	@Autowired
	private CloudinaryService cloudinaryService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file){
        //Map data = this.cloudinaryService.upload(file);
        return new ResponseEntity<>(cloudinaryService.upload(file), HttpStatus.OK);
    }
}
