package com.hcl.ecommerce.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.entity.Category;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.ProductNotFoundException;
import com.hcl.ecommerce.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private IProductRepository productRepository;

	@Override
	public Product addProduct(ProductDto productDto) {
		Product product = new Product();
		User user=new User();
		Category category=new Category();
		user.setUserId(productDto.getUserId());
		category.setCategoryId(productDto.getCategoryId());
	product.setUser(user);
	product.setCategory(category);
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
	public List<Product> getProductsByCategory(String categoryName) throws ProductNotFoundException {
		List<Product> product = productRepository.findByCategory(categoryName);
		System.out.println(product);
		if(product.size()!=0) {
		return product;
		
		}
		else
		{
			throw new ProductNotFoundException("Requested Category not available "+categoryName);
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
