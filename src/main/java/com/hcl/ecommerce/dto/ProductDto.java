package com.hcl.ecommerce.dto;



import java.io.Serializable;

import com.hcl.ecommerce.entity.User;

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

	public class ProductDto implements Serializable {
		private static final long serialVersionUID = 1L; 
	private int productId;
	private String productName;
	private double price;
	private float rating;
	private String category;
	private User user;
}
