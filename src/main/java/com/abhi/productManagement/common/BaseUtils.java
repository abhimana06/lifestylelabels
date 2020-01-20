package com.abhi.productManagement.common;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.abhi.productManagement.entity.TokenEntity;
import com.abhi.productManagement.exception.InvalidTokenException;
import com.abhi.productManagement.repo.TokenRepo;
import com.abhi.productManagement.service.MyUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BaseUtils {
	
	@Autowired
	private TokenRepo tokenRepo;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	public void getUserSession(HttpServletRequest request) throws Exception{
		String authorizationHeader = request.getHeader("Authorization");
		if (WebAppConstant.isSecurityEnabled) {
			if (authorizationHeader == null || !authorizationHeader.contains("Bearer")) {
				log.info("InvalidTokenException");
				throw new InvalidTokenException();
			}
			Object requestIdObj = request.getAttribute("requestId");
			String requestId= Optional.ofNullable(requestIdObj).map(e->e.toString()).orElse(UUID.randomUUID().toString());
			String token = authorizationHeader.substring("Bearer".length()).trim();
			
			TokenEntity jwtToken = tokenRepo.findByAccessToken(token);
			
			if(!token.equalsIgnoreCase(jwtToken.getAccessToken())) {
				throw new InvalidTokenException();
			}
			
		String userName = JWtUtils.getUserIdFromJWT(token);
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
	}
}
