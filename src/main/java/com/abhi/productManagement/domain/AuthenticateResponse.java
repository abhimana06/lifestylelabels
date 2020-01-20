package com.abhi.productManagement.domain;

public class AuthenticateResponse {
	
	private String jwtToken;

	public AuthenticateResponse() {
		
	}
	
	public AuthenticateResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	

}
