package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;

enum Filter {

	BRAND("brand"), COLOR("color"), SIZE("size"), PRICE("price");
	String value;

	Filter(String name) {
		value = name;
	}

	String getValue() {
		return value;
	}
}

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getProducts(String groupByValue, String value) {
		Filter filter = Filter.valueOf(groupByValue.toUpperCase());
		switch (filter) {
		case BRAND:
			return productRepository.findByBrandId(Integer.valueOf(value));
		case COLOR:
			return productRepository.findByColorId(Integer.valueOf(value));
		case SIZE:
			return productRepository.findBySize(value);
		case PRICE:
				return productRepository.findByPrice(Double.valueOf(value));
		default:
			return null;
		}
	}

	@Override
	public List<Product> getProductBySku(String sku) {
		return productRepository.findBySku(sku);
	}

	@Override
	public List<Product> getProductBySeller(Integer sellerId) {
		return productRepository.findBySellerId(sellerId);
	}

}
