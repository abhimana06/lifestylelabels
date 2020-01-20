package com.abhi.productManagement.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhi.productManagement.entity.CategoryEntity;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, Long>{
	
	CategoryEntity findByCategoryName(String name);

}
