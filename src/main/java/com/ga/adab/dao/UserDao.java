package com.ga.adab.dao;



import org.springframework.data.repository.CrudRepository;

import com.ga.adab.model.User;



public interface UserDao extends CrudRepository<User, Integer> {
	public User findByEmailAddress(String emailAddress);
	public User findById(int id);
	public User findByResetPasswordToken(String token);
	
	

	
}
