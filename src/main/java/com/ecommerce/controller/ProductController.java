package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Product;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping("/by-group/{group-name}/{value}")
	public ResponseEntity<List<Product>> getProducts(@PathVariable String groupName,
			@PathVariable String value) {
		List<Product> products = productService.getProducts(groupName, value);
		if(products==null || products.isEmpty()) 
			throw new ProductNotFoundException("Product not found!");
		
		return new ResponseEntity<List<Product>>(products,
				new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/by-sku/{sku}")
	public ResponseEntity<List<Product> > getProductBySku(@PathVariable String sku) {
		List<Product> products = productService.getProductBySku(sku);
		if(products==null || products.isEmpty())
			throw new ProductNotFoundException("Product not found!");

		return new ResponseEntity<List<Product> >(products, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/by-seller/{seller-id}")
	public ResponseEntity<Integer> getProductBySeller(@PathVariable Integer sellerId) {
		List<Product> products = productService.getProductBySeller(sellerId);
		if(products==null || products.isEmpty())
			throw new ProductNotFoundException("Product not found!");

		return new ResponseEntity<Integer>(products.size(), new HttpHeaders(), HttpStatus.OK);
	}

	//http://localhost:8080/api/products/by-group/{group-name}/{group-id} //Groups: brand, price, color and size.
	//http://localhost:8080/api/products/by-sku/{sku}
	//http://localhost:8080/api/products/by-seller/{seller-id}

}