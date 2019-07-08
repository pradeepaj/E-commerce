package com.hcl.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prdouctDetails")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int productId;

	private String productName;

	private double price;
	private float rating;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "U_Id", referencedColumnName = "userId")
	private User user;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "C_Id", referencedColumnName = "categoryId")
	private Category category;

}
