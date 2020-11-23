package com.walmart.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.walmart.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{
	
	public Product findById(Long id);

	public List<Product> findByBrandRegexOrDescriptionRegex(String value1, String value2);

}
