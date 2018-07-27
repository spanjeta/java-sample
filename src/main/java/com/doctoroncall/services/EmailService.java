package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Service
public class EmailService {

	private JavaMailSender mailSender;

	@Autowired
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Async
	public void sendEmail(SimpleMailMessage email) {
		mailSender.send(email);
	}

	public void sendPasswordResetMail(String to, String url) {

		// Email message
		SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
		passwordResetEmail.setFrom("support@demo.com");
		passwordResetEmail.setTo(to);
		passwordResetEmail.setSubject("Password Reset Request");
		passwordResetEmail.setText("To reset your password, click the link below:\n" + url);
		sendEmail(passwordResetEmail);
	}

	public void sendEmailConfirmationMail(String to, String url) {

		// Email message
		SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
		passwordResetEmail.setFrom("support@demo.com");
		passwordResetEmail.setTo(to);
		passwordResetEmail.setSubject("Confirm your email");
		passwordResetEmail.setText("To confirm your email, click the link below:\n" + url);
		sendEmail(passwordResetEmail);
	}
}
