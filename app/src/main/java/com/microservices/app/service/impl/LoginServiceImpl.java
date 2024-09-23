package com.microservices.app.service.impl;

import java.net.URI;
import java.util.Map;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.microservices.app.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	private final DiscoveryClient discoveryClient;
	private final RestTemplate template;

	public LoginServiceImpl(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
		template = new RestTemplate();
	}

	private URI getUri() {
		ServiceInstance serviceInstance = discoveryClient.getInstances("api-gateway").get(0);
		return serviceInstance.getUri();
	}

	@Override
	public LoginResponse authenticateUser(String email, String password) {
		var request = Map.of("email", email, "password", password);
		return template.postForObject(getUri() + "/auth/login", request, LoginResponse.class);
	}

	@Override
	public ResponseEntity<String> sendOtp(String email, String password) {
		var request = Map.of("email", email, "password", password);
		return template.postForEntity(getUri() + "/auth/verifylogin", request, String.class);
	}

	@Override
	public ResponseEntity<String> verifyOtp(String email, String otp) {
		var uri = UriComponentsBuilder.fromHttpUrl(getUri() + "/auth/verify-otp").queryParam("email", email)
				.queryParam("otp", otp).encode().toUriString();

		return template.postForEntity(uri, null, String.class);
	}

}
