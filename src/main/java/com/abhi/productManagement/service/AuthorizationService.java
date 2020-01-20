package com.abhi.productManagement.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface AuthorizationService {

	public Authentication getAuthorization(String userName, String password) ;
}
