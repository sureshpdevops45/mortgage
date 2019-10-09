package com.hcl.mortgage.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class Email {
		
	
   
	public void sendEmail(String emailId, long accountNumber, String randomPassword,JavaMailSender javaMailSender) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailId);
		mail.setSubject("Registered Account Information- Account Number & Password");
		mail.setText("Your Account Number is "+accountNumber +" and the password is "+randomPassword);
        javaMailSender.send(mail);

	}
	
}
