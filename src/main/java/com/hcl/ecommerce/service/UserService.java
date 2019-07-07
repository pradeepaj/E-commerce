package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.UserNotFoundException;

public interface UserService {

	User addUser(UserDto userDto);

	User updateUser(UserDto userDto) throws UserNotFoundException;

	void deleteUser(int userId) throws UserNotFoundException;

	List<User> getUserByRole(String role);

	User userLogin(String userName, String password);

}
