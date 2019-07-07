package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.ProductNotFoundException;

public interface ProductService {

	Product addProduct(ProductDto productDto);

	List<Product> allProducts() throws ProductNotFoundException;

	List<Product> getProductsByCategory(String category) throws ProductNotFoundException;

	List<Product> getProductsByProductName(String productName) throws ProductNotFoundException;

}
