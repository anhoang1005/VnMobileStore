package com.example.ZVnMobile.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.ZVnMobile.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomFilterJwt extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public String getTokenFromHeader(HttpServletRequest request) {

		String header = request.getHeader("Authorization");
		String token = null;
		if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
			token = header.substring(7);
		}
		return token;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getTokenFromHeader(request);
		
		if (token != null) {
			if (jwtUtils.verifyToken(token)) {
				String username = jwtUtils.extractUsername(token);
				List<String> roles = jwtUtils.extractRoles(token);
				List<SimpleGrantedAuthority> authorities = new ArrayList<>();
				for (String role : roles) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                }
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        username, null, authorities);
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//						"", "", new ArrayList<>());
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
