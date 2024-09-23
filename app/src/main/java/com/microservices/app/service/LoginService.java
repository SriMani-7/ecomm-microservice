package com.microservices.app.service;

import org.springframework.http.ResponseEntity;

import com.microservices.app.dto.LoginResponse;

public interface LoginService {

	LoginResponse authenticateUser(String email, String password);

	ResponseEntity<String> sendOtp(String email, String password);

	ResponseEntity<String> verifyOtp(String email, String otp);

}
