package com.abhi.productManagement.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhi.productManagement.entity.CustomerEntity;

@Repository
public interface CustomerRepo  extends JpaRepository<CustomerEntity, Long>{
	
	public Optional<CustomerEntity> findByUserNameIgnoreCase(String userName);
	

}
