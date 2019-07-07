package com.hcl.ecommerce.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private String password;
	private String role;
	

}
