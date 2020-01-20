package com.abhi.productManagement.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductRequest {
	
	private String url;
	
	private String title;
	
	private String imageUrl ;
	
	private String ctgryName;
	
	private String merchant;
	
	private boolean isAvailable ;
	
	private long price;
	
	private long msrp;
	
	private String description;
	
	
	public ProductRequest() {
		
	}
	
	


}
