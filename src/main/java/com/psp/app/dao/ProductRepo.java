package com.psp.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psp.app.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	@Query( value = "select * from products where id = :id", nativeQuery = true)
	Product findProductById(@Param("id") Long id);
}
