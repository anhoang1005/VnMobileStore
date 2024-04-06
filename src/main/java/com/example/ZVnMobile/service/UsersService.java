package com.example.ZVnMobile.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.convert.UsersConverter;
import com.example.ZVnMobile.dto.UserInfoDto;
import com.example.ZVnMobile.dto.UsersDto;
import com.example.ZVnMobile.entities.UsersEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.EditProfileRequest;
import com.example.ZVnMobile.repository.UserRepository;
import com.example.ZVnMobile.service.impl.IMailService;
import com.example.ZVnMobile.service.impl.IUsersService;
import com.example.ZVnMobile.utils.UsersHelperUtils;

@Service
public class UsersService implements IUsersService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsersHelperUtils usersHelperUtils;
	
	@Autowired
	private UsersConverter usersConverter;
	
	@Autowired
	private IMailService iMailService;
	
	@Override
	public DataResponse getAllUsers(){
		DataResponse dataResponse = new DataResponse();
		try {
			List<UsersEntity> listAllUsers = userRepository.findAll();
			List<UsersDto> listUsersDto = new ArrayList<>();
			for(UsersEntity entity : listAllUsers) {
				UsersDto dto = usersConverter.entityToDto(entity);
				listUsersDto.add(dto);
			}
			dataResponse.setData(listUsersDto);
			dataResponse.setSuccess(true);
			dataResponse.setMessage("Get thanh cong!");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			dataResponse.setData(null);
			dataResponse.setSuccess(false);
			dataResponse.setMessage(e.getMessage());
		}
		return dataResponse;
	}

	@Override
	public DataResponse editProfile(EditProfileRequest editProfileRequest) {
		DataResponse dataResponse = new DataResponse();
		UsersEntity usersEntity = new UsersEntity();
		try {
			usersEntity = userRepository.findByEmail(editProfileRequest.getEmail());
			if(usersEntity!=null) {
				usersEntity.setAvatar(editProfileRequest.getAvatar());
				usersEntity.setFullName(editProfileRequest.getFullName());
				usersEntity.setPhoneNumber(editProfileRequest.getPhoneNumber());
				usersEntity.setUpdatedAt(new Date());
				usersEntity = userRepository.save(usersEntity);
				
				dataResponse.setData("ok");
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			dataResponse.setMessage("Loi " + e.getMessage());
			dataResponse.setSuccess(false);
		}
		
		return dataResponse;
	}

	@Override
	public DataResponse changePassword(String email, String password, String newPassword) {
		DataResponse dataResponse = new DataResponse();
		UsersEntity usersEntity = new UsersEntity();
		try {
			usersEntity = userRepository.findByEmail(email);
			if(usersEntity!=null && passwordEncoder.matches(password, usersEntity.getPassword())) {
				String hashPassword = passwordEncoder.encode(newPassword);
				usersEntity.setPassword(hashPassword);
				usersEntity = userRepository.save(usersEntity);
				dataResponse.setSuccess(true);
				dataResponse.setMessage("Doi mat khau thanh cong!");
			}
			else {
				dataResponse.setSuccess(false);
				dataResponse.setMessage("Mat khau khong dung!");
			}
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi " + e.getMessage());
		}
		return dataResponse;
	}

	@Override
	public DataResponse forgotPassword(String email) {
		DataResponse dataResponse = new DataResponse();
		UsersEntity usersEntity = new UsersEntity();
		
		try {
			usersEntity = userRepository.findByEmail(email);
			if(usersEntity!=null) {
				String verifyCode = usersHelperUtils.verifyCode();
				usersEntity.setVerifyCode(verifyCode);
				usersEntity = userRepository.save(usersEntity);
				boolean isSend = iMailService.sendFogotPasswordMail(usersEntity.getFullName(), email, verifyCode);
				if(isSend) {
					dataResponse.setSuccess(true);
					dataResponse.setMessage("Gui Email xac nhan thanh cong!");
				}
				else {
					dataResponse.setMessage("Gui Email xac nhan that bai!");
					dataResponse.setSuccess(false);
				}
			}
		} catch (Exception e) {
			dataResponse.setMessage("Loi: "+ e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse verifyForgotPassword(String email, String verifyCode, String newPassword) {
		DataResponse dataResponse = new DataResponse();
		UsersEntity usersEntity = new UsersEntity();
		try {
			usersEntity = userRepository.findByEmail(email);
			if(usersEntity!=null && verifyCode.equals(usersEntity.getVerifyCode())) {
				String hashPassword = passwordEncoder.encode(newPassword);
				usersEntity.setPassword(hashPassword);
				usersEntity.setVerifyCode(null);
				usersEntity.setUpdatedAt(new Date());
				usersEntity = userRepository.save(usersEntity);
				
				dataResponse.setSuccess(true);
				dataResponse.setMessage("Doi mat khau thanh cong!");
			}
			else {
				dataResponse.setSuccess(false);
				dataResponse.setMessage("Verify code khong dung!");
			}
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
		}
		return dataResponse;
	}

	@Override
	public DataResponse getUserInfoByEmail(String email) {
		DataResponse dataResponse = new DataResponse();
		try {
			UsersEntity usersEntity = userRepository.findByEmail(email);
			UserInfoDto userInfoDto = usersConverter.userInfoEntityToUserInfoDto(usersEntity);
		
			dataResponse.setData(userInfoDto);
			dataResponse.setSuccess(true);
		} catch (Exception e) {
			dataResponse.setMessage("Loi: " + e.getMessage());;
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse checkExistUser(String email) {
		DataResponse dataResponse = new DataResponse();
		try {
			UsersEntity usersEntity = userRepository.findByEmail(email);
			if(usersEntity==null) {
				dataResponse.setSuccess(true);
				dataResponse.setData("Khong ton tai!");
			}
			else {
				dataResponse.setSuccess(false);
				dataResponse.setData("Da ton tai!");
			}
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setMessage("Loi: " + e.getMessage());
			dataResponse.setData("Da xay ra loi!");
		}
		return dataResponse;
	}

	@Override
	public DataResponse lockOrUnlockUser(Long id, boolean lock) {
		DataResponse dataResponse = new DataResponse();
		try {
			UsersEntity usersEntity = userRepository.findOneById(id);
			usersEntity.setDeleted(lock);
			usersEntity = userRepository.save(usersEntity);
			if(usersEntity!=null) {
				dataResponse.setData("OK");
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			dataResponse.setData("Error");
			dataResponse.setMessage("Error: " + e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse adminUpdateRoleUser(Long id, String role) {
		DataResponse dataResponse = new DataResponse();
		try {
			UsersEntity usersEntity = userRepository.findOneById(id);
			usersEntity.setRole(role);
			usersEntity = userRepository.save(usersEntity);
			if(usersEntity!=null){
				dataResponse.setData("OK");
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			dataResponse.setData("Error");
			dataResponse.setMessage("Error: " + e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}
	
	
	
	
}
