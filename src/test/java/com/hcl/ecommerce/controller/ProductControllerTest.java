package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.entity.Category;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.service.IProductService;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestCase.class, ProductController.class })
@WebAppConfiguration
public class ProductControllerTest {

	@InjectMocks
	private ProductController productController;
	@Mock
	private IProductService productService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	public void addProductTest() throws JsonProcessingException, Exception {
		
		ProductDto productDto = new ProductDto("ktm", 2345, 4,1, 1 );
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		ResponseEntity<Product> expResult = new ResponseEntity<>(product, HttpStatus.CREATED);
		when(productService.addProduct(productDto)).thenReturn(product);
		mockMvc.perform(post("/users/products").contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
				.andReturn();
		ResponseEntity<Product> result = productController.addProduct(productDto);
		assertEquals(expResult, result);
	}

	@Test
	public void getAllProductsTest() throws JsonProcessingException, Exception {
		User user = new User();
		Category category = new Category(1, "bike", null);
		Product product = new Product(1, "ktm", 2345, 4, user, category);
		Product product1 = new Product(2, "kt", 2345, 4, user, category);
		List<Product> prolist = new ArrayList<>();
		prolist.add(product);
		prolist.add(product1);
		ResponseEntity<List<Product>> expResult = new ResponseEntity<>(prolist, HttpStatus.OK);
		when(productService.allProducts()).thenReturn(prolist);
		mockMvc.perform(get("/users/products").contentType(MediaType.APPLICATION_JSON).content(asJsonString(prolist)))
				.andReturn();
		ResponseEntity<List<Product>> actResult = productController.allProducts();
		assertEquals(expResult, actResult);
	}

	@Test
	public void getProductsByCategory() throws JsonProcessingException, Exception {
		User user = new User();
		Category category = new Category(1, "bike", null);
		Product product = new Product(1, "ktm", 2345, 4, user, category);
		Product product1 = new Product(2, "kt", 2345, 4, user, category);
		List<Product> prolist = new ArrayList<>();

		ResponseEntity<List<Product>> expResult = new ResponseEntity<>(prolist, HttpStatus.OK);
		when(productService.getProductsByCategory(Mockito.anyString())).thenReturn(prolist);
		mockMvc.perform(get("/users/categories/{category}", "Seller").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(prolist))).andReturn();
		ResponseEntity<List<Product>> actResult = productController.getProductsByCategory("Seller");
		assertEquals(expResult, actResult);
	}

	@Test
	public void getProductsByProductNameTest() throws JsonProcessingException, Exception {
		User user = new User();
		Category category = new Category(1, "bike", null);
		Product product = new Product(1, "ktm", 2345, 4, user, category);
		Product product1 = new Product(2, "kt", 2345, 4, user, category);

		List<Product> prolist = new ArrayList<>();
		ResponseEntity<List<Product>> expResult = new ResponseEntity<>(prolist, HttpStatus.OK);
		when(productService.getProductsByProductName(Mockito.anyString())).thenReturn(prolist);
		mockMvc.perform(get("/users/{productName}", "Bike").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(prolist))).andReturn();
		ResponseEntity<List<Product>> actResult = productController.getProductsByProductName("Bike");
		assertEquals(expResult, actResult);
	}

	public static String asJsonString(final Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);

	}
}
