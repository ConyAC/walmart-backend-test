package com.walmart.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.domain.Product;
import com.walmart.repository.ProductRepository;


@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductRepository repository;
	
	/**
	 * Metodo que permite retornar el resultado de una búsqueda de acuerdo a lo existente en base de datos. 
	 * Discrimina en caso de palindromo.
	 * @param value
	 * @return
	 */
	@PostMapping(value = "/search")
	public List<Product> resultSearch(@RequestParam(required = false,defaultValue = "") String value){
	
		int sale = 0;
		boolean specialProduct = checkSpecialProduct(value);
		
		List<Product> products = repository.findByBrandRegexOrDescriptionRegex(value,value);
		
		for (Product product : products) {
			if(specialProduct) {
				product.setSpecialProduct(true);
				product.setBasePrice(product.getBasePrice());
				sale = Integer.parseInt(product.getPrice())/2;
				product.setPrice(Integer.toString(sale));			
			}
		}
		
		/*
		 * Cuando una búsqueda sea sobre los identificadores de productos, se deberá retornar el resultado exacto.
		 */
		try {
		    Long id = Long.parseLong(value);
		    Product product = repository.findById(id);
		    if(product != null) {
		    	if(specialProduct) {
			    	product.setSpecialProduct(true);
					product.setBasePrice(product.getBasePrice());
					sale = Integer.parseInt(product.getPrice())/2;
					product.setPrice(Integer.toString(sale));
		    	}
		    	return new ArrayList<>(Arrays.asList(product));
		    }
		    	
		} catch (NumberFormatException e) {
		    // str is not a number
		}
		
		return products;
	}
	
	/**
	 * Metodo que busca verificar si una palabra ingresada en la busqueda corresponde a palindromo.
	 * @param s
	 * @return
	 */
	boolean checkSpecialProduct(String s) {
		 int N = s.length();
	     for (int i = 0; i < N/2; i++)
	     if (s.charAt(i) != s.charAt(N-1-i))
	    	 return false;
	     
	     return true; 
	}
}