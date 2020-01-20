package com.abhi.productManagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.productManagement.common.IOUtils;
import com.abhi.productManagement.common.JWtUtils;
import com.abhi.productManagement.common.WebAppConstant;
import com.abhi.productManagement.domain.UserInfo;
import com.abhi.productManagement.entity.TokenEntity;
import com.abhi.productManagement.exception.EntityNotFoundException;
import com.abhi.productManagement.exception.InvalidTokenException;
import com.abhi.productManagement.repo.TokenRepo;
import com.abhi.productManagement.service.AuthTokenService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthTokenServiceImpl implements AuthTokenService{

	@Autowired
	private TokenRepo tokenRepo;
	
	
	@Override
	public String createToken(UserInfo userInfo ) {
		
		final String jwt = JWtUtils.generateToken(userInfo);
		TokenEntity tokenEntity = new TokenEntity();
		tokenEntity.setAccessToken(jwt);
		tokenEntity.setTokenStatus(WebAppConstant.ACTIVE);
		tokenRepo.save(tokenEntity);
		
		return jwt;
	}


	@Override
	public void makeTokenInactive(String authHeader) {
		try {
			if (authHeader == null || !authHeader.contains("Bearer")) {
				throw new InvalidTokenException();
			}
			String token = authHeader.substring("Bearer".length()).trim();
			
			TokenEntity tokenEntity = tokenRepo.findByAccessToken(token);
			if(tokenEntity!=null) {
				tokenEntity.setTokenStatus(WebAppConstant.INACTIVE);
				tokenEntity.setIsActive(false);
			}else {
				throw new EntityNotFoundException();
			}
		}catch(Exception e){
			log.error(IOUtils.getPrintStackTrace(e));
		}
		
		
		
	}

}
