package com.microservices.app.service;

import org.springframework.http.ResponseEntity;

import com.microservices.app.dto.LoginResponse;
import com.microservices.app.dto.OTPVerifyRequest;
import com.microservices.app.dto.RegisterRequest;

public interface LoginService {

	LoginResponse authenticateUser(String email, String password);

	String register(RegisterRequest request);

	ResponseEntity<String> verifyEmail(String email);

	ResponseEntity<String> verifyEmail(OTPVerifyRequest otpVerifyRequest);

}
