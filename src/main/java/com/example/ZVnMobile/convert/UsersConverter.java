package com.example.ZVnMobile.convert;

import org.springframework.stereotype.Component;

import com.example.ZVnMobile.dto.UsersDto;
import com.example.ZVnMobile.entities.UsersEntity;

@Component
public class UsersConverter {
	
	public UsersDto entityToDto(UsersEntity entity) {
		UsersDto dto = new UsersDto();
		dto.setAvatar(entity.getAvatar());
		dto.setFullName(entity.getFullName());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		dto.setRole(entity.getRole());
		dto.setVerifyCode(entity.getVerifyCode());
		dto.setEnable(entity.isEnable());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		dto.setDeleted(entity.isDeleted());
		return dto;
	}
}
