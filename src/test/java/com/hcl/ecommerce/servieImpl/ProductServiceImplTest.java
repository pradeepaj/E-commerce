package com.hcl.ecommerce.servieImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.entity.Category;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.ProductNotFoundException;
import com.hcl.ecommerce.repository.IProductRepository;
import com.hcl.ecommerce.service.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceImplTest {

	@InjectMocks
	private ProductServiceImpl productService;
	@Mock
	private IProductRepository productRepository;

	@Test
	public void addProductTest() {
		User user = new User();
		Category category=new Category(1, "fz", null);
		ProductDto productDto=new ProductDto("ktm", 1234, 4, 1, 1);
		
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		when(productRepository.save(product)).thenReturn(product);
		Product actres = productService.addProduct(productDto);
		assertEquals(product, product);
	}
	@Test
	public void  allProductsTest() throws ProductNotFoundException{
		User user=new User();
		Category category=new Category(1,"bike", null);
		Product product=new Product(1, "ktm", 2345, 4, user,category);
		Product product1=new Product(2, "kt", 2345, 4,user,category);
		List<Product> prolist=new ArrayList<>();
		prolist.add(product);
		prolist.add(product1);
		when(productRepository.findAll()).thenReturn(prolist);
		List<Product> actres=productService.allProducts();
		assertEquals(prolist, actres);
	}
	@Test
	public void  getProductsByCategoryTest() throws ProductNotFoundException{
		User user = new User();
		Category category=new Category(1, "fz", null);
		Product product = new Product(1, "ktm", 2345, 4, user, category);
		Product product1 = new Product(2, "kt", 2345, 4, user, category);
		List<Product> prolist = new ArrayList<>();
		prolist.add(product);
		prolist.add(product1);
		when(productRepository.findByCategory(Mockito.anyString())).thenReturn(prolist);
		List<Product> actres=productService.getProductsByCategory("Bike");
		assertEquals(prolist, actres);
	}
	@Test
	public void getProductsByProductNameTest() throws ProductNotFoundException{
		User user = new User();
		Category category=new Category(1, "fz", null);
		Product product = new Product(1, "ktm", 2345, 4, user, category);
		Product product1 = new Product(2, "kt", 2345, 4, user, category);
		List<Product> prolist = new ArrayList<>();
		prolist.add(product);
		prolist.add(product1);
		when(productRepository.findByProductName(Mockito.anyString())).thenReturn(prolist);
		List<Product> actres=productService.getProductsByProductName("ktm");
		assertEquals(prolist, actres);
	}

}
