package com.hcl.ecommerce.servieImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
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

import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.UserNotFoundException;
import com.hcl.ecommerce.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceImplTest {
	@InjectMocks
	private UserServiceImpl userService;
	@Mock
	private UserRepository userRepository;

	@Test
	public void addUserTest() {
		UserDto userDto = new UserDto(1, "aj", "aj", "Seller");
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		when(userRepository.save(user)).thenReturn(user);

		User actRes = userService.addUser(userDto);
		System.out.println(actRes);

		assertEquals(user, user);

	}

	@Test
	public void updateUser() {

		UserDto userDto = new UserDto(1, "aj", "aj", "Seller");
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		when(userRepository.save(user)).thenReturn(user);
		User actRes = userService.addUser(userDto);

		assertEquals(user, user);
	}

	@Test
	public void deleteUserTest() throws UserNotFoundException {
		UserDto userDto = new UserDto(1, "aj", "aj", "Seller");
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		doNothing().when(userRepository).deleteById(user.getUserId());
		
	}

	@Test
	public void getUserByRoleTest() {
		User user = new User(1, "aj", "aj", "Seller", null);
		User user1 = new User(2, "pradi", "pradi", "Seller", null);
		List<User> users = new ArrayList<>();
		when(userRepository.findByRole(Mockito.anyString())).thenReturn(users);
		List<User> users1 = userService.getUserByRole("Seller");
		assertEquals(users, users1);
	}

	@Test
	public void userLoginTest() {
		User user = new User(1, "aj", "aj", "Seller", null);
		when(userRepository.findByUserNameAndPassword("aj", "aj")).thenReturn(user);
		User actres = userService.userLogin("aj", "aj");
		assertEquals(user, actres);
	}
}
