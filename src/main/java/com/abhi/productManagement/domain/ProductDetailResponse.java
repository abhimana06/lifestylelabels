package com.abhi.productManagement.domain;
import java.util.List;

import com.abhi.productManagement.entity.CategoryEntity;
import com.abhi.productManagement.entity.MerchantEntity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ProductDetailResponse {

	
	private Long productId;
	
	private String url;
	
	private String title;
	
	private String imageUrl;
	
	private String categoryName;
	
	private Long price;
	
	private Long msrp;
	
	private Boolean isAvailable;
	
	private String merchantName;
	
	private String description;


}
