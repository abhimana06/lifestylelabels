package com.abhi.productManagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.abhi.productManagement.service.AuthorizationService;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public Authentication getAuthorization(String userName, String password) {
		
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		return authentication;
	}
	

}
