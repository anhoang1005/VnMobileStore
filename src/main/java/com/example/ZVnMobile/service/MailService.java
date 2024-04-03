package com.example.ZVnMobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.ZVnMobile.service.impl.IMailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService implements IMailService{
	
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${server.url}")
	private String serverURL;

	@Override
	public boolean sendEmail(String to, String subject, String body) {
		boolean isSucess = false;
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
	        message.setSubject(subject);
	        message.setText(body);
	        javaMailSender.send(message);
	        isSucess = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return isSucess;
	}

	@Override
	public boolean sendVerifyEmail(String toAdress, String verifyCode) {
		boolean isSucess = false;
		String toAddress = toAdress;
	    String fromAddress = fromEmail;
	    String senderName = "VnMobile";
	    String subject = "Mã xác nhận tài khoản người dùng VnMobile";
	    String content = "Xin chào [[name]],<br>"
	            + "Đây là mã xác nhận để kích hoạt tài khoản người dùng VnMobile của bạn, vui lòng không để lộ ra ngoài!:<br>"
	            + "<h4>Mã xác nhận: [[verifyCode]]</h4><br>"
	            + "Cảm ơn bạn,<br>"
	            + "[[Company]]";
	    
	    try {
	    	MimeMessage message = javaMailSender.createMimeMessage();
		    MimeMessageHelper helper = new MimeMessageHelper(message);
		    
		    helper.setFrom(fromAddress, senderName);
		    helper.setTo(toAddress);
		    helper.setSubject(subject);
		    content = content.replace("[[name]]", toAddress);
		    content = content.replace("[[verifyCode]]", verifyCode);
		    content = content.replace("[[Company]]", senderName);
		    helper.setText(content, true);
		    javaMailSender.send(message);
		    
		    isSucess = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return isSucess;
	}

	@Override
	public boolean sendFogotPasswordMail(String fullName, String email, String verifyCode) {
		boolean isSucess = false;
		String toAddress = email;
	    String fromAddress = fromEmail;
	    String senderName = "VNMobile";
	    String subject = "Mã xác nhận đổi mặt khẩu tài khoản VnMobile";
	    String content = "Xin chào [[name]],<br>"
	            + "Đây là mã đổi mật khẩu tạm thời của bạn, vui lòng không để lộ ra ngoài!<br>"
	            + "<h4>Mã xác nhận: [[Password]]</h4><br>"
	            + "Cảm ơn bạn,<br>"
	            + "[[Company]]";
	    
	    try {
	    	MimeMessage message = javaMailSender.createMimeMessage();
		    MimeMessageHelper helper = new MimeMessageHelper(message);
		    
		    helper.setFrom(fromAddress, senderName);
		    helper.setTo(toAddress);
		    helper.setSubject(subject);
		    content = content.replace("[[name]]", fullName);
		    content = content.replace("[[Password]]", verifyCode);
		    content = content.replace("[[Company]]", senderName);
		    helper.setText(content, true);
		    javaMailSender.send(message);
		    
		    isSucess = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return isSucess;
	}
	
}
