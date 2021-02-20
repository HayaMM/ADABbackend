package com.ga.adab.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ga.adab.dao.UserDao;
import com.ga.adab.model.User;

@RestController
public class UserController {
	@Autowired
	private Environment env;
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	HttpServletRequest request;
	
	// To post the registration form
	 @PostMapping("/user/registration")
	 public HashMap<String, String> registration(@RequestBody User user) {

		 HashMap<String, String> response = new HashMap<String, String> ();
		 // Check to user is already registered or not
		 var it = dao.findAll();
		 
		 for(User dbUser : it) {
			 if(dbUser.getEmailAddress().equals(user.getEmailAddress())) {
				 response.put("message", "User already exists");
				 return response;
			 }
		 }
		 
		 // Password Encryption
		 BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		 String newPassword = bCrypt.encode(user.getPassword());
		 user.setPassword(newPassword);
		 dao.save(user);
		 response.put("message", "User registered successfully");
		 return response;
		 
	 }
}
