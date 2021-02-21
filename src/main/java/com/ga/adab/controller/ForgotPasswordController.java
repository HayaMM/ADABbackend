package com.ga.adab.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ga.adab.model.User;
import com.ga.adab.service.UserDetailsServiceImpl;
import com.ga.adab.service.UserNotFoundException;

import net.bytebuddy.utility.RandomString;

@RestController
public class ForgotPasswordController {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UserDetailsServiceImpl Service;

	
	//method for mapping the post forgot password page
	@PostMapping("/user/forgotpassword")
	public boolean processForgotPassword(HttpServletRequest request, Model model ,@RequestBody ObjectNode json) {

		 
		String email = json.get("emailAddress").asText();  
		String token = RandomString.make(30);
		System.out.println(email);
		System.out.println(token);

		try {
			Service.updateResetPasswordToken(token, email);
			// make reset password link
//			String siteURL = request.getRequestURL().toString();
			String resetPasswordLink = HomeController.getSiteURL(request) + "/user/reset_password?token=" + token;
			// send the email
			sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
			System.out.println("link " + resetPasswordLink);

		} catch (UserNotFoundException ex) {
			model.addAttribute("error", ex.getMessage());
		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error", "Error while sending email");
		}

		
		return true;
	}

	// method for sending an email to the user email
	public void sendEmail(String email, String resetPasswordLink)
			throws MessagingException, UnsupportedEncodingException {

		System.out.println("------------------------------in send email");
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("contact@igdb.com", "ADAB Support");
		helper.setTo(email);
		String subject = "Here's the link to reset your password";

		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + resetPasswordLink
				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";
		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

	//method for mapping the reset password page
//	@GetMapping("/user/reset_password")
//	public ModelAndView ResetPassword(@Param(value="token") String token, Model model) {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("user/reset_password");
//		
//		User user = Service.getByResetPasswordToken(token);
//		if(user == null) {
//			model.addAttribute("message", "Invalid Token");
//			return mv;
//		}
//		model.addAttribute("token", token);
//		
//		return mv;
//
//	}
 // method for mapping the post of the rest password page and change the old password with the new password
	@PostMapping("/user/reset_password")
	public boolean ResetPassword(HttpServletRequest request, Model model,@RequestBody ObjectNode json) {
		
		System.out.println("------------------------------in reset_password");

		String token = request.getParameter("token");
//				json.get("token").asText();  
		String newPassword = json.get("password").asText(); 
		System.out.println(token);
		System.out.println(newPassword);
		User user = Service.getByResetPasswordToken(token);

		if(user == null) {
			model.addAttribute("updatemessage", "Invalid Token");
			return false;
		}else {
			Service.updatePassword(user, newPassword);
			model.addAttribute("updatemessage", "You Have Successfully changed your password");
		}
		return true;
		
	}

}