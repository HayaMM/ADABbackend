package com.ga.adab.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ga.adab.config.JwtUtil;
import com.ga.adab.dao.UserDao;
import com.ga.adab.model.JwtResponse;
import com.ga.adab.model.User;
import com.ga.adab.service.MyService;



@RestController
public class UserController {

	  private static final Logger logger = LoggerFactory.getLogger("MyController.class");
	    @Autowired
	    private MyService myService;

	@Autowired
	private UserDao dao;

	@Autowired
	HttpServletRequest request;

	// To post the registration form
	@PostMapping("/user/registration")
	public HashMap<String, String> registration(@RequestBody User user) {

		HashMap<String, String> response = new HashMap<String, String>();
		// Check to user is already registered or not
		var it = dao.findAll();

		for (User dbUser : it) {
			if (dbUser.getEmailAddress().equals(user.getEmailAddress())) {
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

	// HTTP Put REQUEST - User Edit
	@PutMapping("/user/edit")
	public User editUser(@RequestBody User user) {
		dao.save(user);
		return user;
	}
	
	// Load user profile
	@GetMapping("/user/profile")
	public User userProfile(@RequestParam String emailAddress) {
		User user = dao.findByEmailAddress(emailAddress);
		user.getEmailAddress();
		return user;
	}
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	UserDetailsService userDetailsService;

	@PostMapping("/user/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody User user) {
System.out.print("auth");
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmailAddress(), user.getPassword()));
		} catch (BadCredentialsException e) {
			String res = "Incorrect username or password";
			return ResponseEntity.ok(res);
		}

		// Conitnue
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmailAddress());
		String jwtToken = jwtUtil.generateToken(userDetails);
		System.out.println(jwtToken);
		return ResponseEntity.ok(new JwtResponse(jwtToken));

	}

	// HTTP GET REQUEST - User changepassword
	@PostMapping("/user/changepassword")
	public User changepassword(@RequestParam int id, @RequestBody ObjectNode json) {

		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		User user = dao.findById(id);
		String Password = json.get("password").asText();
		String newPassword = json.get("newPassword").asText();

		if (bCrypt.matches(Password, user.getPassword())) {
			String cryptPassword = bCrypt.encode(newPassword);
			user.setPassword(cryptPassword);
			dao.save(user);
			System.out.println("equless");

		} else {
			System.out.println("not equless");
		}


		return user;
	}
		

		 
		// HTTP DELETE REQUEST - User Delete
			@DeleteMapping("/user/delete")
			public boolean deleteAccount(@RequestParam int id) {
				dao.deleteById(id);
				return true;
			}

			// HTTP UPLOUD REQUEST - user uploud image 
			  @PostMapping("user/image/fileupload")
			    public User fileUpload(@RequestParam("id") int id, @RequestParam("file") MultipartFile file) {
				  User user = dao.findById(id); 
				  
				  try {
			            logger.info("id= " + id);
			            byte[] image = file.getBytes(); 
			            User newuser = new User(id, image);
			            int saveImage = myService.saveImage(newuser , user);
			            if (saveImage == 1) {
			                return user;
			            } else {
			                return user;
			            }
			        } catch (Exception e) {
			            logger.error("ERROR", e);
			            return user;
			        }
			    }
			  @GetMapping("/getimg")
			    public byte[] getDbDetils( int id) {
				  User user = dao.findById(id);
			        try {
			            logger.info("Id= " + id);
			            byte[] encode = java.util.Base64.getEncoder().encode(user.getImage());
			            return encode;
			        } catch (Exception e) {
			            logger.error("Error", e);
			            return null;
			        }
			    }
}
