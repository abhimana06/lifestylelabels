package com.abhi.productManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abhi.productManagement.entity.TokenEntity;

@Repository
public interface TokenRepo extends JpaRepository<TokenEntity, Long>{

	TokenEntity findByAccessToken(String string);

	/*
	 * @Query(" UPDATE TokenEntity c set  c.isActive=false and c.tokenStatus=:tokenStatus "
	 * ) void updateStatus(@Param("tokenStatus") String tokenStatus);
	 */

}
