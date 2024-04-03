package com.example.ZVnMobile.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private CustomFilterJwt customFilterJwt;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable());
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(customFilterJwt, UsernamePasswordAuthenticationFilter.class);
        http.authorizeHttpRequests(requests -> requests
                                .requestMatchers(
                                		"/api/user/**",
                                		"/api/category/**",
                                		"/api/supplier/**",
                                		"/api/product/**",
                                		"/api/review/**").permitAll()
                                .requestMatchers(
                                		"/api/admin/**").hasRole("QUANLI")
                                .requestMatchers(
                                		"/api/customer/**").hasRole("KHACHHANG")
                                .anyRequest().authenticated());
		return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsService() throws Exception{
		UserDetails admin = User.withUsername("test")
				.password(passwordEncoder().encode("0000"))
				.roles("TEST").build();
		return new InMemoryUserDetailsManager(admin);
	}
}
