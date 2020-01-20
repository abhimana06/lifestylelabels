package com.abhi.productManagement.service;


import org.springframework.stereotype.Service;

import com.abhi.productManagement.domain.ProductRequest;
import com.abhi.productManagement.domain.ProductResponse;
import com.abhi.productManagement.entity.ProductEntity;

@Service
public interface ProductService {
	
	public ProductResponse getProduct();

	public void addProduct(ProductRequest request);

	public ProductEntity updateProduct(ProductRequest request, Long id);

	public void deleteProduct(Long id);

	public ProductResponse searchProduct(String productTitle);


	
	
	

}
