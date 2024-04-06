package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.payload.DataResponse;
import com.example.ZVnMobile.payload.request.EditProfileRequest;

public interface IUsersService {
	//DAng nhap dang ki
	DataResponse getUserInfoByEmail(String email);
	DataResponse checkExistUser(String email);
	DataResponse editProfile(EditProfileRequest editProfileRequest);
	DataResponse changePassword(String email, String password, String newPassword);
	DataResponse forgotPassword(String email);
	DataResponse verifyForgotPassword(String email,String verifyCode, String newPassword);
	
	//Admin
	DataResponse getAllUsers();
	DataResponse lockOrUnlockUser(Long id, boolean lock);
	DataResponse adminUpdateRoleUser(Long id, String role);
	
//	DataResponse getByRole(String role, int pageNumber);
//	DataResponse getByLockStatus(boolean deleted, int pageNumber);
//	DataResponse getBySearch(String txtkey, int pageNumber);
}
