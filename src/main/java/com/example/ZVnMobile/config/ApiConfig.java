package com.example.ZVnMobile.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.cloudinary.Cloudinary;

@Configuration
public class ApiConfig {
	
	@Value("${cloudinary.cloudName}")
	private String cloudName;
	
	@Value("${cloudinary.apiKey}")
	private String apiKey;
	
	@Value("${cloudinary.apiSecret}")
	private String apiSecret;
	
	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@SuppressWarnings("unchecked")
	@Bean
	Cloudinary getCloudinary(){
        @SuppressWarnings("rawtypes")
		Map config = new HashMap();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
