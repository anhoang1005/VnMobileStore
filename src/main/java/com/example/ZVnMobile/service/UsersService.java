package com.example.ZVnMobile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.ZVnMobile.convert.LoginConverter;
import com.example.ZVnMobile.convert.UsersConverter;
import com.example.ZVnMobile.dto.UserInfoDto;
import com.example.ZVnMobile.dto.UsersDto;
import com.example.ZVnMobile.entities.UsersEntity;
import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.JwtTokenResponse;
import com.example.ZVnMobile.payload.request.EditProfileRequest;
import com.example.ZVnMobile.repository.UserRepository;
import com.example.ZVnMobile.service.impl.IMailService;
import com.example.ZVnMobile.service.impl.IUsersService;
import com.example.ZVnMobile.utils.UsersHelperUtils;

@Service
public class UsersService implements IUsersService {

	@Value("${jwt.existenceTime}")
	private Long existenceiTime;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginConverter loginConverter;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsersHelperUtils usersHelperUtils;

	@Autowired
	private UsersConverter usersConverter;

	@Autowired
	private IMailService iMailService;

	@Autowired
	private CloudinaryService cloudinaryService;

	@Override
	public DataResponse getAllUsers() {
		DataResponse dataResponse = new DataResponse();
		try {
			List<UsersEntity> listAllUsers = userRepository.findAll();
			List<UsersDto> listUsersDto = new ArrayList<>();
			for (UsersEntity entity : listAllUsers) {
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
			dataResponse.setErrorCode(e.getMessage());
		}
		return dataResponse;
	}

	@Override
	public DataResponse editProfile(EditProfileRequest editProfileRequest) {
		DataResponse dataResponse = new DataResponse();
		UsersEntity usersEntity = new UsersEntity();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Principal principal = (Principal) authentication.getPrincipal();
//		System.out.println(principal.getName());
		if (authentication == null || !authentication.getName().equals(editProfileRequest.getEmail())) {
			dataResponse.setMessage("Error");
			dataResponse.setErrorCode("UnAuthentication!");
			dataResponse.setSuccess(false);
			return dataResponse;
		}
		try {
			usersEntity = userRepository.findByEmail(editProfileRequest.getEmail());
			if (usersEntity != null) {
				usersEntity.setFullName(editProfileRequest.getFullName());
				usersEntity.setPhoneNumber(editProfileRequest.getPhoneNumber());
				usersEntity = userRepository.save(usersEntity);

				JwtTokenResponse jwtTokenResponse = loginConverter.userEntityToJwtToken(usersEntity, existenceiTime);
				dataResponse.setData(jwtTokenResponse);
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			dataResponse.setMessage("Error");
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setSuccess(false);
		}

		return dataResponse;
	}

	@Override
	public DataResponse changeAvatar(String email, MultipartFile file) {
		DataResponse dataResponse = new DataResponse();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.getName().equals(email)) {
			dataResponse.setMessage("Error");
			dataResponse.setErrorCode("UnAuthentication!");
			dataResponse.setSuccess(false);
			return dataResponse;
		}
		try {
			UsersEntity user = userRepository.findByEmail(email);
			if (user != null) {
				DataResponse uploadFile = cloudinaryService.upload(file);
				if (uploadFile.isSuccess() == true) {
					user.setAvatar((String) uploadFile.getData());
					user = userRepository.save(user);
					JwtTokenResponse jwtTokenResponse = loginConverter.userEntityToJwtToken(user, existenceiTime);
					dataResponse.setSuccess(true);
					dataResponse.setData(jwtTokenResponse);
					dataResponse.setMessage("Đổi avatar thành công!");
				} else {
					dataResponse.setMessage("Không thể upload ảnh!");
					dataResponse.setErrorCode(uploadFile.getErrorCode());
					dataResponse.setSuccess(false);
				}
			}
		} catch (Exception e) {
			dataResponse.setMessage("Error");
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	@Transactional
	public DataResponse changePassword(String email, String password, String newPassword) {
		DataResponse dataResponse = new DataResponse();
		UsersEntity usersEntity = new UsersEntity();
		try {
			usersEntity = userRepository.findByEmail(email);
			if (usersEntity != null && passwordEncoder.matches(password, usersEntity.getPassword())) {
				String hashPassword = passwordEncoder.encode(newPassword);
				usersEntity.setPassword(hashPassword);
				usersEntity = userRepository.save(usersEntity);

				JwtTokenResponse jwtTokenResponse = loginConverter.userEntityToJwtToken(usersEntity, existenceiTime);
				dataResponse.setData(jwtTokenResponse);
				dataResponse.setSuccess(true);
				dataResponse.setMessage("Đổi mật khẩu thành công!");
			} else {
				dataResponse.setSuccess(false);
				dataResponse.setMessage("Mật khẩu không đúng!");
			}
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setErrorCode(e.getMessage());
		}
		return dataResponse;
	}

	@Override
	public DataResponse forgotPassword(String email) {
		DataResponse dataResponse = new DataResponse();
		UsersEntity usersEntity = new UsersEntity();

		try {
			usersEntity = userRepository.findByEmail(email);
			if (usersEntity != null) {
				String verifyCode = usersHelperUtils.verifyCode();
				usersEntity.setVerifyCode(verifyCode);
				usersEntity = userRepository.save(usersEntity);
				boolean isSend = iMailService.sendFogotPasswordMail(usersEntity.getFullName(), email, verifyCode);
				if (isSend) {
					dataResponse.setSuccess(true);
					dataResponse.setMessage("Gửi email xác nhận thành công!");
				} else {
					dataResponse.setMessage("Gửi email xác nhận thất bại!");
					dataResponse.setSuccess(false);
				}
			}
		} catch (Exception e) {
			dataResponse.setErrorCode(e.getMessage());
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
			if (usersEntity != null && verifyCode.equals(usersEntity.getVerifyCode())) {
				String hashPassword = passwordEncoder.encode(newPassword);
				usersEntity.setPassword(hashPassword);
				usersEntity.setVerifyCode(null);
				// usersEntity.setUpdatedAt(new Date());
				usersEntity = userRepository.save(usersEntity);

				dataResponse.setSuccess(true);
				dataResponse.setMessage("Đổi mật khẩu thành công!");
			} else {
				dataResponse.setSuccess(false);
				dataResponse.setMessage("Mã xác nhận bạn nhập không đúng!");
			}
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setErrorCode(e.getMessage());
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
			dataResponse.setErrorCode(e.getMessage());
			;
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

	@Override
	public DataResponse checkExistUser(String email) {
		DataResponse dataResponse = new DataResponse();
		try {
			UsersEntity usersEntity = userRepository.findByEmail(email);
			if (usersEntity == null) {
				dataResponse.setSuccess(true);
				dataResponse.setData("Email này chưa tồn tại!");
			} else {
				dataResponse.setSuccess(false);
				dataResponse.setData("Email đã tồn tại!");
			}
		} catch (Exception e) {
			dataResponse.setSuccess(false);
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setData("Error");
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
			if (usersEntity != null) {
				dataResponse.setMessage("Cập nhật trạng thái thành công");
				;
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			dataResponse.setData("Error");
			dataResponse.setErrorCode(e.getMessage());
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
			if (usersEntity != null) {
				dataResponse.setData("Phân quyền thành công!");
				dataResponse.setSuccess(true);
			}
		} catch (Exception e) {
			dataResponse.setData("Error");
			dataResponse.setErrorCode(e.getMessage());
			dataResponse.setSuccess(false);
		}
		return dataResponse;
	}

}
