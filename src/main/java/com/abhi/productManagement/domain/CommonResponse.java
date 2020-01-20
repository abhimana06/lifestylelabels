package com.abhi.productManagement.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {
	
	private String responseCode;
	
	private String responseMessage;

	
	public CommonResponse() {
		super();
		
	}
	
	public CommonResponse(String responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	
	
	

}
