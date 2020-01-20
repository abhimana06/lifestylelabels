package com.abhi.productManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;




@ResponseStatus(HttpStatus.NOT_FOUND)

public class EntityNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	

	public EntityNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public EntityNotFoundException(String e) {
		super(e);
	}

	
	

}
