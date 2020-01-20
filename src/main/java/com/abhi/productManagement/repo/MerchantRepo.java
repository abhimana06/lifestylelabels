package com.abhi.productManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhi.productManagement.entity.MerchantEntity;

@Repository
public interface MerchantRepo extends JpaRepository<MerchantEntity, Long>{

	 MerchantEntity findByMerchantName(String name);
}
