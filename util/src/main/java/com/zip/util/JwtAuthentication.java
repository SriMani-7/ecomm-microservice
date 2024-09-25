package com.zip.util;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;

@Component
public class JwtAuthentication {

	@Autowired
	private JwtUtil jwtUtil;

	public UsernamePasswordAuthenticationToken authenticationFromHeader(String header) {

		String token = header;

		if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			Claims claims = jwtUtil.getClaims(token.substring(7));

			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(claims.getIssuer());
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					claims.getSubject(), null, Collections.singleton(authority));
			return authentication;
		}
		return null;
	}
}