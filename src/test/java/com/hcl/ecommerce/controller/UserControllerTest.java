package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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
import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.UserNotFoundException;
import com.hcl.ecommerce.service.UserService;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestCase.class, UserController.class })
@WebAppConfiguration
public class UserControllerTest {
	@InjectMocks
	private UserController userController;
	@Mock
	private UserService userService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void addUserTest() throws JsonProcessingException, Exception {
		UserDto userDto = new UserDto(1, "aj", "aj", "Seller");
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		ResponseEntity<User> expResult = new ResponseEntity<>(user, HttpStatus.CREATED);
		when(userService.addUser(userDto)).thenReturn(user);
		mockMvc.perform(post("/users/sign-up").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andReturn();
		ResponseEntity<User> result = userController.addUser(userDto);
		assertEquals(expResult, result);
	}

	@Test
	public void updateUserTest() throws JsonProcessingException, Exception {
		UserDto userDto = new UserDto(1, "aj", "aj", "Seller");
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		ResponseEntity<User> expResult = new ResponseEntity<>(user, HttpStatus.OK);
		when(userService.updateUser(userDto)).thenReturn(user);
		mockMvc.perform(put("/users/update").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andReturn();
		ResponseEntity<User> result = userController.updateUser(userDto);
		assertEquals(expResult, result);
	}

	@Test
	public void deleteUserTest() throws Exception {

		doNothing().when(userService).deleteUser(1);
		mockMvc.perform(put("/users/{userId}", 1).contentType(MediaType.APPLICATION_JSON)).andReturn();
		ResponseEntity<String> user = userController.deleteUser(1);
		assertEquals(200, user.getStatusCodeValue());

	}

	@Test
	public void getUserByRole() throws JsonProcessingException, Exception {
		User user = new User(1, "aj", "aj", "Seller", null);
		User user1 = new User(2, "pradi", "pradi", "Seller", null);
		List<User> users = new ArrayList<>();
		ResponseEntity<List<User>> expResult = new ResponseEntity<List<User>>(users, HttpStatus.OK);
		users.add(user);
		users.add(user1);
		when(userService.getUserByRole(Mockito.anyString())).thenReturn(users);
		mockMvc.perform(put("/users/{userId}", 1).contentType(MediaType.APPLICATION_JSON).content(asJsonString(users)))
				.andReturn();
		ResponseEntity<List<User>> actaulResult = userController.getUserByRole("Seller");
		assertEquals(expResult, actaulResult);

	}

	@Test(expected = UserNotFoundException.class)
	public void updateUserTestExceptionTest() throws UserNotFoundException {
		UserDto userDto = new UserDto(100, "aj", "aj", "Seller");
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		when(userService.updateUser(userDto)).thenThrow(new UserNotFoundException("Data not found"));
		userController.updateUser(userDto);
	}

	public static String asJsonString(final Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);

	}
}
