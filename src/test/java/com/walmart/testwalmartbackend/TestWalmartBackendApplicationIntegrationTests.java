package com.walmart.testwalmartbackend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.walmart.domain.Product;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableSpringDataWebSupport
public class TestWalmartBackendApplicationIntegrationTests {
	
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    
    @Test
    public void find_by_id() {
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	
    	MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
    	map.add("value", "1");
    	
    	HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,headers);
    	
    	ResponseEntity<List<Product>> response  = 
    			testRestTemplate.exchange("http://localhost:"+port+"/products/search",HttpMethod.POST, request, new ParameterizedTypeReference<List<Product>>() {});
    	
    	
    	List<Product> body = response .getBody();  
    	
    	assertEquals(1,body.size());
    	assertEquals(1L,body.get(0).getId());
    	
    }
    
    @Test
    public void find_by_palindromium() {
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	
    	MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
    	map.add("value", "saas");
    	
    	HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,headers);
    	
    	ResponseEntity<List<Product>> response  = 
    			testRestTemplate.exchange("http://localhost:"+port+"/products/search",HttpMethod.POST, request, new ParameterizedTypeReference<List<Product>>() {});
    	
    	
    	List<Product> body = response .getBody();  
    	
    	assertEquals(21,body.size());
    	assertEquals(true,body.get(0).isSpecialProduct());
    	assertEquals(true,body.get(1).isSpecialProduct());
    	
    }
    
    @Test
    public void find_by_a_not_palindromium() {
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	
    	MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
    	map.add("value", "saa");
    	
    	HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,headers);
    	
    	ResponseEntity<List<Product>> response  = 
    			testRestTemplate.exchange("http://localhost:"+port+"/products/search",HttpMethod.POST, request, new ParameterizedTypeReference<List<Product>>() {});
    	
    	
    	List<Product> body = response .getBody();  
    	
    	assertEquals(21,body.size());
    	assertEquals(false,body.get(0).isSpecialProduct());
    	assertEquals(false,body.get(1).isSpecialProduct());
    	
    }

}
