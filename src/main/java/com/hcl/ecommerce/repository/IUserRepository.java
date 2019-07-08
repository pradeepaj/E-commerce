package com.hcl.ecommerce.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.User;
@Repository
public interface IUserRepository extends JpaRepository<User,Integer> {

	User findByUserId(int userId);
    @Modifying
    @Transactional
	void deleteByUserId(int userId);

	List<User> findByRole(String role);


	User findByUserNameAndPassword(String userName, String password);

}
