package com.example.ZVnMobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ZVnMobileApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZVnMobileApplication.class, args);
	}

}
