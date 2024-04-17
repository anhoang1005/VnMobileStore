package com.example.ZVnMobile.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.example.ZVnMobile.payload.DataResponse;

@Service
public class CloudinaryService {

	private final Cloudinary cloudinary;
	
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
	
	@SuppressWarnings("rawtypes")
	public DataResponse upload(MultipartFile file)  {
		DataResponse dataResponse = new DataResponse();
        try{
            Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());
            dataResponse.setData(data);
            dataResponse.setSuccess(true);
        }catch (IOException io){
        	dataResponse.setMessage("Error");
            dataResponse.setSuccess(false);
            dataResponse.setErrorCode(io.getMessage());
            throw new RuntimeException("Image upload fail");
        }
        return dataResponse;
    }
	
}
