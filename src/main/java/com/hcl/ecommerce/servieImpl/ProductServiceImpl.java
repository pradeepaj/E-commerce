package com.hcl.ecommerce.servieImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.ProductNotFoundException;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(ProductDto productDto) {
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		return productRepository.save(product);

	}

	@Override
	public List<Product> allProducts() throws ProductNotFoundException {

		List<Product> product = productRepository.findAll();
		if(product.size()!=0) {
			return product;
			}
			else
			{
				throw new ProductNotFoundException("Products not available ");
			}
	}

	@Override
	public List<Product> getProductsByCategory(String category) throws ProductNotFoundException {
		List<Product> product = productRepository.findByCategory(category);
		if(product.size()!=0) {
		return product;
		}
		else
		{
			throw new ProductNotFoundException("Requested Category not available "+category);
		}
	}

	@Override
	public List<Product> getProductsByProductName(String productName) throws ProductNotFoundException {
		List<Product> product = productRepository.findByProductName(productName);
		if(product.size()!=0) {
			return product;
			}
			else
			{
				throw new ProductNotFoundException("Requested Product not available "+productName);
			}
	}

}
