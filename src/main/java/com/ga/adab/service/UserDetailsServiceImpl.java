package com.ga.adab.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ga.adab.dao.UserDao;
import com.ga.adab.model.User;
import com.ga.adab.model.UserDetailsImp;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserDao dao;
	@Override
	public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException{
		User user = dao.findByEmailAddress(emailAddress);
		
		if(user == null)
			throw new UsernameNotFoundException("Not found");
		
		UserDetailsImp userDetailsObj = new UserDetailsImp(user);
		return userDetailsObj;
	}

	     
	 
	    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
	        User user = dao.findByEmailAddress(email);
	        if (user != null) {
	        	user.setResetPasswordToken(token);
	            dao.save(user);
	        } else {
	            throw new UserNotFoundException("Could not find any User with the email " + email);
	        }
	    }
	     
	    public User getByResetPasswordToken(String token) {
	        return dao.findByResetPasswordToken(token);
	    }
	     
	    public void updatePassword(User user, String newPassword) {
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encodedPassword = passwordEncoder.encode(newPassword);
	        user.setPassword(encodedPassword);
	         
	        user.setResetPasswordToken(null);
	        dao.save(user);
	    }
}
