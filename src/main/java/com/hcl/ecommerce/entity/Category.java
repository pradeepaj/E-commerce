package com.hcl.ecommerce.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categoryDetails")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	@JsonIgnore
	private String categoryName;
	@JsonIgnore
	@OneToMany(targetEntity=Product.class, mappedBy="category",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Product> product;
	

}
