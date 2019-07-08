package com.hcl.ecommerce.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "userDetails")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

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
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="user")
    private List<Product> product;

}
