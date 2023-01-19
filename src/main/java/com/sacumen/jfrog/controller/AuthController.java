package com.sacumen.jfrog.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sacumen.jfrog.commons.AppConstants;
import com.sacumen.jfrog.jwt.JwtTokenUtil;
import com.sacumen.jfrog.request.LoginRequest;
import com.sacumen.jfrog.response.JwtResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
				authenticationRequest.getPassword(),
				new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(AppConstants.ROLE_ADMIN)))));

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		LOG.info("User Logged in {}", userDetails.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
}
