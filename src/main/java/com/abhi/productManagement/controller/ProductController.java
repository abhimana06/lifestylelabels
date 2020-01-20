package com.abhi.productManagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.mapstruct.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.productManagement.annotation.AuthSecurity;
import com.abhi.productManagement.common.BaseUtils;
import com.abhi.productManagement.common.IOUtils;
import com.abhi.productManagement.common.WebAppConstant;
import com.abhi.productManagement.domain.CommonResponse;
import com.abhi.productManagement.domain.ProductRequest;
import com.abhi.productManagement.domain.ProductResponse;
import com.abhi.productManagement.service.ProductService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;
	
	@Autowired
	private BaseUtils baseUtils;
	
	
	@GetMapping("/getProducts")
	@AuthSecurity(enabled = WebAppConstant.isSecurityEnabled)
	@ApiOperation(value = "To get all Products", notes = "Just pass Valid Authorization Token")
	public ResponseEntity<?> getProduct (@RequestHeader("Authorization") String autHeader, @Context HttpServletRequest httpRequest) throws Exception{
		ProductResponse response = new ProductResponse();
		try {	
			baseUtils.getUserSession(httpRequest);
			response = productService.getProduct();
			response.setResponseCode(WebAppConstant.SUCCESS_REPONSE_CODE);
			response.setResponseMessage(WebAppConstant.SUCCESS_REPONSE_MESSAGE);
		}catch(Exception e) {
			logger.error(IOUtils.getPrintStackTrace(e));
			response.setResponseCode(WebAppConstant.FAILURE_REPONSE_CODE);
			response.setResponseMessage(WebAppConstant.FAILURE_REPONSE_MESSAGE);
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/add")
	@AuthSecurity(enabled = WebAppConstant.isSecurityEnabled)
	@ApiOperation(value = "To add new Product", notes = "ctgryName and merchant valid entires should be thier in DB ")
	public ResponseEntity<?> addProduct(@RequestHeader("Authorization") String autHeader, @Context HttpServletRequest httpRequest, @RequestBody ProductRequest request ) {
		try {
			baseUtils.getUserSession(httpRequest);
			productService.addProduct(request);
			return ResponseEntity.ok(new CommonResponse(WebAppConstant.SUCCESS_REPONSE_CODE,WebAppConstant.SUCCESS_REPONSE_MESSAGE));
			
		}catch(Exception e) {
			logger.error(IOUtils.getPrintStackTrace(e));
			return ResponseEntity.ok(new CommonResponse(WebAppConstant.FAILURE_REPONSE_CODE,WebAppConstant.FAILURE_REPONSE_MESSAGE));		
			}
		
	}
	
	@PutMapping("/update")
	@AuthSecurity(enabled = WebAppConstant.isSecurityEnabled)
	@ApiOperation(value = "To update Product", notes = "Pass in ProductId")
	public ResponseEntity<?> updateProduct(@RequestHeader("Authorization") String autHeader, @Context HttpServletRequest httpRequest,@RequestBody ProductRequest request,@RequestParam("productId") Long id ) {
		try {
			baseUtils.getUserSession(httpRequest);
			productService.updateProduct(request,id);
			return ResponseEntity.ok(new CommonResponse(WebAppConstant.SUCCESS_REPONSE_CODE,WebAppConstant.SUCCESS_REPONSE_MESSAGE));

		}catch(Exception e) {
			 
			return ResponseEntity.ok(new CommonResponse(WebAppConstant.FAILURE_REPONSE_CODE,WebAppConstant.FAILURE_REPONSE_MESSAGE));		

		}
		
	}
	
	
	@GetMapping("/search")
	@AuthSecurity(enabled = WebAppConstant.isSecurityEnabled)
	@ApiOperation(value = "Search for Product", notes = "Pass the product title")
	public ResponseEntity<ProductResponse> searchProduct(@RequestHeader("Authorization") String autHeader, @Context HttpServletRequest httpRequest,@RequestParam("title") String title ) {
		ProductResponse response = new ProductResponse();
		try {
			baseUtils.getUserSession(httpRequest);
			response = productService.searchProduct(title);
			response.setResponseCode(WebAppConstant.SUCCESS_REPONSE_CODE);
			response.setResponseMessage(WebAppConstant.SUCCESS_REPONSE_MESSAGE);
		}catch(Exception e) {
			logger.error(IOUtils.getPrintStackTrace(e));
			response.setResponseCode(WebAppConstant.FAILURE_REPONSE_CODE);
			response.setResponseMessage(WebAppConstant.FAILURE_REPONSE_MESSAGE);
		}
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping
	@AuthSecurity(enabled = WebAppConstant.isSecurityEnabled)
	@ApiOperation(value = "To delete Product", notes = "Pass the valid Id and the response will make product unava")
	public ResponseEntity<?> deleteProduct(@RequestHeader("Authorization") String autHeader, @Context HttpServletRequest httpRequest,@RequestParam("productId") Long id ) {
		ProductResponse response = new ProductResponse();
		try {
			 baseUtils.getUserSession(httpRequest);
			 productService.deleteProduct(id);
			return ResponseEntity.ok(new CommonResponse(WebAppConstant.SUCCESS_REPONSE_CODE,WebAppConstant.SUCCESS_REPONSE_MESSAGE));

		}catch(Exception e) {
			logger.error(IOUtils.getPrintStackTrace(e));
			return ResponseEntity.ok(new CommonResponse(WebAppConstant.FAILURE_REPONSE_CODE,WebAppConstant.FAILURE_REPONSE_MESSAGE));		
		}
	}
	

	
}
