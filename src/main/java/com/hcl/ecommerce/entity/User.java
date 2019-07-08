package com.hcl.ecommerce.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "userDetails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@JsonIgnore
	@NotBlank
	private String userName;
	@NotBlank
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String role;
	@JsonIgnore
	@OneToMany(targetEntity=Product.class, mappedBy="user",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> product;

}
