package com.example.ZVnMobile.utils;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class UsersHelperUtils {
	public String temporaryPassword() {
		String character = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder password = new StringBuilder();
		SecureRandom random = new SecureRandom();
		
		for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(character.length());
            password.append(character.charAt(randomIndex));
        }
		return password.toString();
	}
	
	public String verifyCode() {
		String character = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder password = new StringBuilder();
		SecureRandom random = new SecureRandom();
		
		for (int i = 0; i < 20; i++) {
            int randomIndex = random.nextInt(character.length());
            password.append(character.charAt(randomIndex));
        }
		return password.toString();
	}
}
