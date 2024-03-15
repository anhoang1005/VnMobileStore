package com.example.ZVnMobile.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.dto.SignupDto;
import com.example.ZVnMobile.entities.UsersEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.repository.UserRepository;
import com.example.ZVnMobile.service.impl.ILoginService;
import com.example.ZVnMobile.service.impl.IMailService;
import com.example.ZVnMobile.utils.JwtUtils;
import com.example.ZVnMobile.utils.UsersHelperUtils;

@Service
public class LoginService implements ILoginService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UsersHelperUtils usersHelperUtils;
	
	@Autowired
	private IMailService iMailService;

	@Override
	public DataResponse loginUser(String username, String password) {
		DataResponse dataResponse = new DataResponse();
		try {
			UsersEntity user = userRepository.findByEmail(username);
			if(user!=null && passwordEncoder.matches(password, user.getPassword())) {
				if(user.isEnable()==true && user.isDeleted()==true) {
					List<String> roles = new ArrayList<>();
					roles.add(user.getRole());
					
					dataResponse.setSuccess(true);
					dataResponse.setMessage("Thang cong!");
					dataResponse.setData(jwtUtils.generateTokens(user.getEmail(), roles));
				}
				else {
					dataResponse.setSuccess(false);
					dataResponse.setMessage("Tai khoan da bi khoa!");
					dataResponse.setData(null);
				}
			}
			else {
				dataResponse.setSuccess(false);
				dataResponse.setMessage("Sai tai khoan hoac mat khau!");
				dataResponse.setData(null);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
			dataResponse.setData(null);
		}
		return dataResponse;
	}

	@Override
	public DataResponse signupUser(SignupDto signupDto) {
		DataResponse dataResponse = new DataResponse();
		UsersEntity entity = new UsersEntity();
		entity.setFullName(signupDto.getFullName());
		entity.setEmail(signupDto.getEmail());
		
		String hashPassword = passwordEncoder.encode(signupDto.getPassword());
		entity.setPassword(hashPassword);
		entity.setPhoneNumber(signupDto.getPhoneNumber());
		entity.setRole("USER");
		entity.setCreatedAt(new Date());
		entity.setEnable(false);
		entity.setDeleted(true);
		entity.setVerifyCode("123456");
		try {
			String verifyCode = usersHelperUtils.verifyCode();
			boolean isSend = iMailService.sendVerifyEmail(signupDto.getEmail(), verifyCode);
			if(isSend) {
				entity.setVerifyCode(verifyCode);
				entity = userRepository.save(entity);
				dataResponse.setSuccess(true);
				dataResponse.setMessage("Gui email xac nhan thnh cong!");
				dataResponse.setData(null);
			}
			else {
				dataResponse.setSuccess(false);
				dataResponse.setMessage("Gui email xac nhan that bai!");
				dataResponse.setData(null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			entity = userRepository.save(entity);
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
			dataResponse.setData(null);
		}
		return dataResponse;
	}

	@Override
	public DataResponse verifyUsers(String verify, String email) {
		DataResponse dataResponse = new DataResponse();
		UsersEntity usersEntity = new UsersEntity();
		try {
			usersEntity = userRepository.findByEmail(email);
			if(usersEntity!=null && verify.equals(usersEntity.getVerifyCode())) {
				usersEntity.setEnable(true);
				usersEntity.setVerifyCode(null);
				usersEntity.setUpdatedAt(new Date());
				usersEntity = userRepository.save(usersEntity);
				
				dataResponse.setSuccess(true);
				dataResponse.setMessage("Xac nhan thanh cong!");
			}
			else {
				dataResponse.setSuccess(false);
				dataResponse.setMessage("Xac nhan that bai!");
			}
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		return dataResponse;
	}
	
	

}
