package com.hcl.ecommerce.dto;

import java.io.Serializable;

import com.hcl.ecommerce.entity.Category;
import com.hcl.ecommerce.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
public class ProductDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String productName;
	private double price;
	private float rating;
	private User user;
	private Category category;

}
