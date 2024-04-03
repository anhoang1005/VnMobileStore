package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.EditProfileRequest;

public interface IUsersService {
	DataResponse getAllUsers();
	DataResponse getUserInfoByEmail(String email);
	DataResponse checkExistUser(String email);
	DataResponse editProfile(EditProfileRequest editProfileRequest);
	DataResponse changePassword(String email, String password, String newPassword);
	DataResponse forgotPassword(String email);
	DataResponse verifyForgotPassword(String email,String verifyCode, String newPassword);
}
