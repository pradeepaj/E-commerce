package com.hcl.ecommerce.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.UserNotFoundException;
import com.hcl.ecommerce.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepository userRepository;

	@Override
	public User addUser(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);

		if (user != null) {

			String decodedBytes = Base64.getEncoder().encodeToString(user.getPassword().getBytes());

			user.setPassword(new String(decodedBytes));

		}

		return userRepository.save(user);
	}

	@Override
	public User updateUser(UserDto userDto) throws UserNotFoundException {
		User user = userRepository.findByUserId(userDto.getUserId());
		if (user != null) {
			BeanUtils.copyProperties(userDto, user);
			userRepository.save(user);

			return user;

		} else {
			throw new UserNotFoundException("Please enter valid User Details  " + userDto.getUserId());
		}
	}

	@Override
	public void deleteUser(int userId) throws UserNotFoundException {
		User user = userRepository.findByUserId(userId);
		if (user != null) {
			userRepository.deleteByUserId(user.getUserId());
		} else {
			throw new UserNotFoundException("Please enter valid User Details " + userId);
		}

	}

	@Override
	public List<User> getUserByRole(String role) {
		List<User> user = userRepository.findByRole(role);
		return user;
	}

	@Override
	public User userLogin(String userName, String password) throws UserNotFoundException {

		Base64.Encoder encoder = Base64.getEncoder();
		String encodedPassword = encoder.encodeToString(password.getBytes());

		User user = userRepository.findByUserNameAndPassword(userName, encodedPassword);

		if (user != null) {
			byte[] decodedBytes = Base64.getDecoder().decode(user.getPassword());

			user.setPassword(new String(decodedBytes));

			return user;
		} else {
			throw new UserNotFoundException(" Please Enter Valid Credentials");
		}
	}
}
