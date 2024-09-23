package com.microservices.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.microservices.app.dto.OTPVerifyRequest;
import com.microservices.app.dto.RegisterRequest;
import com.microservices.app.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginRegisterController {

	@Autowired
	private LoginService service;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
		try {
			var response = service.authenticateUser(email, password);
			session.setAttribute("token", response.getToken());

			switch (response.getRole().toString()) {
			case "ROLE_RETAILER":
				return "redirect:/retailer";
			case "ROLE_CUSTOMER":
				return "redirect:/products";
			case "ROLE_ADMIN":
				return "redirect:/admin";
			default:
				session.invalidate();
				return "redirect:/";
			}
		} catch (HttpClientErrorException e) {
			model.addAttribute("errorMessage", "Authentication failed: " + e.getResponseBodyAsString());
			return "login";
		} catch (HttpServerErrorException e) {
			model.addAttribute("errorMessage", "Server error: " + e.getResponseBodyAsString());
			return "login";
		} catch (Exception e) {
			model.addAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
			return "login";
		}
	}

	@GetMapping("/register")
	public String showRegistrationForm() {
		return "register"; // return the registration JSP page
	}

	@PostMapping("/register")
	public String registerCustomer(@ModelAttribute RegisterRequest request, Model model) {
		try {
			String message = service.register(request);
			model.addAttribute("successMessage", message);
			return "redirect:/login"; // redirect to login page on success
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "register"; // return to registration page on error
		}
	}

	@PostMapping(value = "/register/verify-email")
	@ResponseBody
	public ResponseEntity<String> verifyEmail(@RequestParam String email) {
		System.out.println("in request for " + email);
		return service.verifyEmail(email);
	}

	@PutMapping("/register/verify-email")
	@ResponseBody
	public ResponseEntity<String> verifyEmailOTP(@RequestBody OTPVerifyRequest otpVerifyRequest) {
		return service.verifyEmail(otpVerifyRequest);
	}

}