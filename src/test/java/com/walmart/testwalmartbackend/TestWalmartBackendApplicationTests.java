package com.walmart.testwalmartbackend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.walmart.domain.Product;
import com.walmart.repository.ProductRepository;

@SpringBootTest
class TestWalmartBackendApplicationTests {
	
	@Autowired
	ProductRepository repository;
	
	@Test
	void find_by_id() {
		Long id = 5L;
		Product product = repository.findById(id);
		assertNotEquals(0, product != null);
		assertEquals(5, product.getId().longValue());
	}
	
	@Test
	void find_all() {
		List<Product> products = repository.findAll();
		assertNotNull(products);
		assertNotEquals(0, products.size());
		assertEquals(3000, products.size());
	}
	
	@Test
	void find_by_term_in_brand() {
		String specialProduct = "dsaasd";
		List<Product> products = repository.findByBrandRegexOrDescriptionRegex(specialProduct,specialProduct);
		assertNotEquals(0, products.size());
		assertEquals(16, products.size());
	}
}
