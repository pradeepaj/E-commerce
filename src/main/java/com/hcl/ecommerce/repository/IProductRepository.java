package com.hcl.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.category.categoryName=:categoryName")
	//@Query(value="select  from ecommerce.prdouct_details p where p.category_name=?1",nativeQuery = true)
	//@Query(value="select * from petpeers.pets p where ownerid=?1",nativeQuery = true)
	List<Product> findByCategory( @Param(value = "categoryName") String categoryName);

	List<Product> findByProductName(String productName);

}
