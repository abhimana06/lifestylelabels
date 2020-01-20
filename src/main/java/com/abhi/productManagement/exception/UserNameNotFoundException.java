package com.abhi.productManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;




@ResponseStatus(HttpStatus.NOT_FOUND)

public class UserNameNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	

	public UserNameNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public UserNameNotFoundException(String e) {
		super(e);
	}

	
	

}
