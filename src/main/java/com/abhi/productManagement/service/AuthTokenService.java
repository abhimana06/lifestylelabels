package com.abhi.productManagement.service;

import org.springframework.stereotype.Service;

import com.abhi.productManagement.domain.UserInfo;

@Service
public interface AuthTokenService {
	
	public String createToken(UserInfo userInfo);

	public void makeTokenInactive(String autHeader);
	

}
