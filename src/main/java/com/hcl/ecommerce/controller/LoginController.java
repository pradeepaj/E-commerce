package com.hcl.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.UserNotFoundException;
import com.hcl.ecommerce.service.IUserService;

@RestController
@RequestMapping("/user")
public class LoginController {
	
	@Autowired
	private IUserService userService;
	
	
	@GetMapping("/login")
	public ResponseEntity<User> userLogin(@RequestHeader(value = "userName") String userName, @RequestHeader(value = "password") String password) throws UserNotFoundException{
		User user=userService.userLogin(userName,password);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}

	
}
