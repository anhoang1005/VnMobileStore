package com.example.ZVnMobile.service.impl;

import com.example.ZVnMobile.dto.SignupDto;
import com.example.ZVnMobile.payload.DataResponse;

public interface ILoginService {
	DataResponse signinUser(String username, String password);
	DataResponse signupUser(SignupDto signupDto);
	DataResponse verifyUsers(String verify, String email);
}
