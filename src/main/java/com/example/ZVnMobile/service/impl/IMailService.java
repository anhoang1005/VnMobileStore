package com.example.ZVnMobile.service.impl;

public interface IMailService {
	boolean sendEmail(String to, String subject, String body);
	boolean sendVerifyEmail(String toAdress, String verifyCode);
	boolean sendFogotPasswordMail(String fullName, String email, String verifyCode);
}
