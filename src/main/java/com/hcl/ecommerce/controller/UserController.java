package com.hcl.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.UserDto;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.UserNotFoundException;
import com.hcl.ecommerce.service.IUserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class UserController {

	@Autowired
	private IUserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {
		User user = userService.addUser(userDto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody UserDto userDto) throws UserNotFoundException {
		User user = userService.updateUser(userDto);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId) throws UserNotFoundException {
		userService.deleteUser(userId);
		return new ResponseEntity<>("User Deleted Successfully ", HttpStatus.OK);
	}

	@GetMapping("/{role}")
	public ResponseEntity<List<User>> getUserByRole(@PathVariable String role) {
		List<User> user = userService.getUserByRole(role);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

}
