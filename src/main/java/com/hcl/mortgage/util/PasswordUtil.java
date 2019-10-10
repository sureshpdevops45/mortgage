package com.hcl.mortgage.util;

import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

	public String encodePassword(String password) {
		System.out
				.println(Base64.getEncoder().encodeToString(password.getBytes()) + "--------------------------------");
		return Base64.getEncoder().encodeToString(password.getBytes());

	}
}
