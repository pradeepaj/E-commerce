package com.hcl.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.ProductNotFoundException;
import com.hcl.ecommerce.service.IProductService;

@RestController
@RequestMapping("/users")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })

public class ProductController {

	@Autowired
	private IProductService productService;

	@PostMapping("/products/add")
	public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {
		Product product = productService.addProduct(productDto);
		return new ResponseEntity<>(product, HttpStatus.CREATED);

	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> allProducts() throws ProductNotFoundException {
		List<Product> product = productService.allProducts();
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/products/category/{categoryName}")
	public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryName) throws ProductNotFoundException {
		List<Product> product = productService.getProductsByCategory(categoryName);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/products/{productName}")
	public ResponseEntity<List<Product>> getProductsByProductName(@PathVariable String productName) throws ProductNotFoundException {
		List<Product> product = productService.getProductsByProductName(productName);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

}
