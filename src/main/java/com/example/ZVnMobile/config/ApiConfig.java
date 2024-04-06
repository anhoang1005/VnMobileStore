package com.example.ZVnMobile.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.cloudinary.Cloudinary;

@Configuration
public class ApiConfig {
	
	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@SuppressWarnings("unchecked")
	@Bean
	Cloudinary getCloudinary(){
        @SuppressWarnings("rawtypes")
		Map config = new HashMap();
        config.put("cloud_name", "dcejjvibg");
        config.put("api_key", "266324919786388");
        config.put("api_secret", "7EPYhFvTuHioFPD5lt1NsHMzyDI");
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
