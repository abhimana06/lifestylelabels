package com.abhi.productManagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.productManagement.common.IOUtils;
import com.abhi.productManagement.common.WebAppConstant;
import com.abhi.productManagement.domain.ProductDetailResponse;
import com.abhi.productManagement.domain.ProductRequest;
import com.abhi.productManagement.domain.ProductResponse;
import com.abhi.productManagement.entity.CategoryEntity;
import com.abhi.productManagement.entity.MerchantEntity;
import com.abhi.productManagement.entity.ProductEntity;
import com.abhi.productManagement.exception.BadRequestException;
import com.abhi.productManagement.exception.EntityNotFoundException;
import com.abhi.productManagement.repo.CategoryRepo;
import com.abhi.productManagement.repo.MerchantRepo;
import com.abhi.productManagement.repo.ProductRepo;
import com.abhi.productManagement.service.ProductService;

import lombok.extern.slf4j.Slf4j;;
		

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService{

	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private MerchantRepo MerchantRepo;
	
	@Override
	public ProductResponse getProduct() {
		ProductResponse response = new ProductResponse();
		List<ProductDetailResponse> detailTypeList = null;
		try {
			List<ProductEntity> prdEntities = productRepo.getProducts();
			
			if(prdEntities!=null && prdEntities.size()>0) {
				detailTypeList = prdEntities.stream().map(mapper->mapProduct(mapper)).collect(Collectors.toList());
				response.setDetailResponse(detailTypeList);
			}else {
				throw new EntityNotFoundException();
			}
			
		}catch(Exception e) {
			IOUtils.getPrintStackTrace(e);
			
		}
		return response;
	}
	
	@Override
	public void addProduct(ProductRequest request) {
		
		try {
			ProductEntity entity = new ProductEntity();
			addOrUpdateEntity(request, entity);
		}catch(Exception e){
			logger.error(IOUtils.getPrintStackTrace(e));
		}
		
	}

	@Override
	public ProductEntity updateProduct( ProductRequest request, Long id) {
		ProductEntity prdEntity = null;
		try {
			 prdEntity = productRepo.findById(id).orElseThrow(()->new BadRequestException(WebAppConstant.INVALID_ID));
			 addOrUpdateEntity(request,prdEntity);
			
		}catch(Exception e) {
			IOUtils.getPrintStackTrace(e);
			
		}
		return prdEntity;
		
	}
	
	private void addOrUpdateEntity(ProductRequest request, ProductEntity entity) {
		try {
			if(request.getCtgryName()==null || request.getCtgryName().isEmpty()) {
				throw new BadRequestException();
			}
			
			CategoryEntity categoryEntity = categoryRepo.findByCategoryName(request.getCtgryName());
			
			if(request.getMerchant()==null || request.getMerchant().isEmpty()) {
				throw new BadRequestException();
			}
			MerchantEntity merchantEntity  = MerchantRepo.findByMerchantName(request.getMerchant());
			
			
			 
			  entity.setCategory(categoryEntity); 
			  entity.setDescription(request.getDescription());
			  entity.setMerchant(merchantEntity);
			  entity.setPrice(request.getPrice());
			  entity.setTitle(request.getTitle());
			  entity.setUrl(request.getUrl());
			  entity.setMsrp(request.getMsrp());
			  entity.setImgUrl((WebAppConstant.IMAGE_URL)+"/" +request.getImageUrl());
			  
			  productRepo.save(entity);
		}catch(Exception e){
			logger.error(IOUtils.getPrintStackTrace(e));
		}
		
	}

	@Override
	public void deleteProduct(Long id) {
		ProductEntity prdEntity = null;
		try {
			 prdEntity = productRepo.findById(id).orElseThrow(()->new BadRequestException(WebAppConstant.INVALID_ID));
			 prdEntity.setAvailable(false);
			 prdEntity.setIsActive(false);
			
		}catch(Exception e) {
			IOUtils.getPrintStackTrace(e);
			
		}
		
	}

	@Override
	public ProductResponse searchProduct(String title) {
		ProductResponse response = new ProductResponse();
		List<ProductDetailResponse> detailTypeList = null;
		try {
			List<ProductEntity> prdEntities = productRepo.findByTitle(title);
			if(prdEntities!=null && prdEntities.size()>0) {
				detailTypeList = prdEntities.stream().map(mapper->mapProduct(mapper)).collect(Collectors.toList());
				response.setDetailResponse(detailTypeList);
			}else {
				throw new EntityNotFoundException();
			}
			
		}catch(Exception e) {
			logger.error(IOUtils.getPrintStackTrace(e));
			
		}
		return response;
	}

	private ProductDetailResponse mapProduct(ProductEntity mapper) {
		ProductDetailResponse detailType = new ProductDetailResponse();
		log.info("mapProduct");
		detailType.setCategoryName(mapper.getCategory().getCategoryName()); 
		detailType.setDescription(mapper.getDescription());
		detailType.setMerchantName(mapper.getMerchant().getMerchantName());
		detailType.setPrice(mapper.getPrice());
		detailType.setTitle(mapper.getTitle());
		detailType.setUrl(mapper.getUrl());
		detailType.setIsAvailable(mapper.isAvailable());
		detailType.setImageUrl((WebAppConstant.IMAGE_URL)+ "/"+mapper.getImgUrl());
		log.info("detailType"+detailType.toString());
		return detailType;
	}
	

}
