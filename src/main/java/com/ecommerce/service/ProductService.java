package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.Product;

public interface ProductService {
	List<Product> getProductBySeller(Integer sellerId);
	List<Product> getProducts(String groupName, String value);
	List<Product> getProductBySku(String sku);
}
