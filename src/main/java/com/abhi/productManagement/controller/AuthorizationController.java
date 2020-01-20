package com.abhi.productManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.productManagement.common.IOUtils;
import com.abhi.productManagement.common.WebAppConstant;
import com.abhi.productManagement.domain.AuthenticateResponse;
import com.abhi.productManagement.domain.AuthenticationRequest;
import com.abhi.productManagement.domain.CommonResponse;
import com.abhi.productManagement.domain.UserInfo;
import com.abhi.productManagement.service.AuthTokenService;
import com.abhi.productManagement.service.AuthorizationService;

import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
@RequestMapping("/authorization")
public class AuthorizationController {

	@Autowired
	private AuthorizationService authorizationService;
	
	@Autowired
	private AuthTokenService authTokenService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	
	@PostMapping("/authenticate")
	public AuthenticateResponse createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
		AuthenticateResponse response = new AuthenticateResponse();
		log.info("call");
		try {
			 Authentication authentication = authorizationService.getAuthorization(request.getUsername(),request.getPassword());		 
					 
			 SecurityContextHolder.getContext().setAuthentication(authentication);
			 
			 UserInfo userInfo = (UserInfo)authentication.getPrincipal();
			 log.info("output");
			 String jwt = authTokenService.createToken(userInfo);
			 response.setJwtToken(jwt);
		}catch(BadCredentialsException e) {
			throw new Exception("Invalid UserName and Password", e);
		}
		return response;
	}
	
	@PostMapping("/logout")
	public ResponseEntity<CommonResponse> logout(@RequestHeader("Authorization") String autHeader) throws Exception {
		try {
			authTokenService.makeTokenInactive(autHeader);
			return ResponseEntity.ok(new CommonResponse(WebAppConstant.SUCCESS_REPONSE_CODE,WebAppConstant.SUCCESS_REPONSE_MESSAGE));
			
		}catch(Exception e) {
			log.error(IOUtils.getPrintStackTrace(e));
			return ResponseEntity.ok(new CommonResponse(WebAppConstant.FAILURE_REPONSE_CODE,WebAppConstant.FAILURE_REPONSE_MESSAGE));		
			}
		
	}
	

}
