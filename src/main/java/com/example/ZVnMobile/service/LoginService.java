package com.example.ZVnMobile.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.convert.LoginConverter;
import com.example.ZVnMobile.dto.SignupDto;
import com.example.ZVnMobile.entities.UsersEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.JwtTokenResponse;
import com.example.ZVnMobile.repository.UserRepository;
import com.example.ZVnMobile.service.impl.ILoginService;
import com.example.ZVnMobile.service.impl.IMailService;
import com.example.ZVnMobile.utils.UsersHelperUtils;

@Service
public class LoginService implements ILoginService {
	
	@Value("${jwt.existenceTime}")
	private Long existenceTime;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private LoginConverter loginConverter;

	@Autowired
	private UsersHelperUtils usersHelperUtils;

	@Autowired
	private IMailService iMailService;

	@Override
	public DataResponse signinUser(String username, String password) {
		DataResponse dataResponse = new DataResponse();
		try {
			UsersEntity user = userRepository.findByEmail(username);
			if (user != null && passwordEncoder.matches(password, user.getPassword())) {
				if (user.isEnable() == true && user.isDeleted() == true) {
					List<String> roles = new ArrayList<>();
					roles.add(user.getRole());
					JwtTokenResponse jwtTokenResponse = loginConverter.userEntityToJwtToken(user, existenceTime);
					dataResponse.setSuccess(true);
					dataResponse.setMessage("Đăng nhập thành công!");
					dataResponse.setData(jwtTokenResponse);
				} else if(user.isEnable() == false && user.isDeleted()==true){
					dataResponse.setSuccess(false);
					dataResponse.setMessage("Tài khoản chưa xác thực!");
				} else if(user.isDeleted() == false && user.isEnable() == true){
					dataResponse.setSuccess(false);
					dataResponse.setMessage("Tài khoản đã bị khóa!");
				}
				else if(user.isDeleted() == false && user.isEnable() == false){
					dataResponse.setSuccess(false);
					dataResponse.setMessage("Tài khoản của bạn đã bị khóa do chưa xác thực!");
					dataResponse.setData(null);
				}
			} else {
				dataResponse.setSuccess(false);
				dataResponse.setMessage("Sai tài khoản hoặc mật khẩu!");
				dataResponse.setData(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			dataResponse.setSuccess(false);
			dataResponse.setErrorCode(e.getMessage());
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
		entity.setRole("KHACHHANG");
		entity.setCreatedAt(new Date());
		entity.setEnable(false);
		entity.setDeleted(true);
		entity.setAvatar("/img/avatar/user.png");
		entity.setVerifyCode("123456");
		entity.setCreatedBy(signupDto.getEmail());
		try {
			String verifyCode = usersHelperUtils.verifyCode();
			boolean isSend = iMailService.sendVerifyEmail(signupDto.getEmail(), verifyCode);
			if (isSend) {
				entity.setVerifyCode(verifyCode);
				entity = userRepository.save(entity);
				dataResponse.setSuccess(true);
				dataResponse.setMessage("Gửi email xác nhận thành công!");
				dataResponse.setData(null);
			} else {
				dataResponse.setSuccess(false);
				dataResponse.setMessage("Gửi email xác nhận thất bại!");
				dataResponse.setData(null);
			}
		} catch (Exception e) {
			//entity = userRepository.save(entity);
			dataResponse.setSuccess(false);
			dataResponse.setErrorCode(e.getMessage());
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
			if (usersEntity != null && verify.equals(usersEntity.getVerifyCode())) {
				usersEntity.setEnable(true);
				usersEntity.setVerifyCode(null);
				//usersEntity.setUpdatedAt(new Date());
				usersEntity = userRepository.save(usersEntity);
				dataResponse.setSuccess(true);
				dataResponse.setMessage("Xac nhan thanh cong!");
			} else {
				dataResponse.setSuccess(false);
				dataResponse.setMessage("Xac nhan that bai!");
			}
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Error");
			dataResponse.setErrorCode(e.getMessage());
		}
		return dataResponse;
	}

	

}
