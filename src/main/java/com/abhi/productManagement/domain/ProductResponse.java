package com.abhi.productManagement.domain;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ProductResponse {
	
  
  List<ProductDetailResponse> detailResponse;
  
  private String responseCode; 
	
  private String responseMessage; 
	
}
