package com.example.ZVnMobile.convert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ZVnMobile.entities.UsersEntity;
import com.example.ZVnMobile.payload.JwtTokenResponse;
import com.example.ZVnMobile.utils.JwtUtils;

@Component
public class LoginConverter {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public JwtTokenResponse userEntityToJwtToken(UsersEntity user, Long expires) {
		JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();
		List<String> roles = new ArrayList<>();
		roles.add(user.getRole());
		jwtTokenResponse.setEmail(user.getEmail());
		jwtTokenResponse.setFullName(user.getFullName());
		jwtTokenResponse.setRole(user.getRole());
		jwtTokenResponse.setTokenType("Bearer");
		jwtTokenResponse.setIssuedAt(new Date());
		jwtTokenResponse.setAvatar(user.getAvatar());
		jwtTokenResponse.setExpiresAt(new Date(System.currentTimeMillis() + expires * 1000));
		jwtTokenResponse.setToken(jwtUtils.generateTokens(user.getEmail(), roles));
		return jwtTokenResponse;
	}
}
