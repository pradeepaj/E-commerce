package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.service.IUserService;

import junit.framework.TestCase;
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestCase.class, LoginController.class })
@WebAppConfiguration
public class LoginControllerTest {

	@InjectMocks
	private LoginController loginController;
	@Mock
	private IUserService userService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
	}
	
	@Test
	public void userLoginTest() throws Exception {
		User user = new User(1, "aj", "aj", "Seller", null);
		
		ResponseEntity<User> expResult = new ResponseEntity<>(user, HttpStatus.OK);
		when(userService.userLogin("aj","aj")).thenReturn(user);
		mockMvc.perform(get("/users/login","aj","aj").contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		ResponseEntity<User> result = loginController.userLogin("aj","aj");
		assertEquals(expResult, result);
	}

}
