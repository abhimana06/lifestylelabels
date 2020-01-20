package com.abhi.productManagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abhi.productManagement.entity.ProductEntity;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long>{

	
	
	@Query(" Select c from ProductEntity c where c.isAvailable=true")
	List<ProductEntity> getProducts();

	@Query(" Select c from ProductEntity c where c.title=:title")
	List<ProductEntity> findByTitle(@Param("title") String title);
	

}
