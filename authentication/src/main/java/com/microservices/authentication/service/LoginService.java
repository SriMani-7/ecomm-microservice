package com.microservices.authentication.service;

import com.microservices.authentication.dto.UserDTO;

public interface LoginService {

	String registerUser(UserDTO user);

	String sendOtp(String email, String password);

	String verifyEmail(String email);

	String updatePassword(String email, String password);

	String existsByEmail(String email);

	String verifyOtp(String email, String otp);

	String verifyOtpForLogin(String email, String otp);

}
