package com.example.ZVnMobile.utils;

import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	
	@Value("${jwt.secretKeyString}")
	private String secretKeyString;
	
	public String generateTokens(String username, List<String> roles) {
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKeyString));
		String jws = Jwts.builder()
				.subject(username)
				.claim("roles", roles)
				.signWith(key)
				.compact();
		return jws;
	}
	
	public boolean verifyToken(String token) {
		try {
			SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKeyString));
			Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> extractRoles(String token) {
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKeyString));
		@SuppressWarnings("deprecation")
		Claims claims = Jwts.parser()
				.verifyWith(key).build().parseSignedClaims(token).getBody();
		return claims.get("roles", List.class);
    }
	
	public String extractUsername(String token) {
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKeyString));
		
		@SuppressWarnings("deprecation")
		String username = Jwts.parser()
							.verifyWith(key)
							.build()
							.parseSignedClaims(token).getBody().getSubject();
		return username;
	}
}
