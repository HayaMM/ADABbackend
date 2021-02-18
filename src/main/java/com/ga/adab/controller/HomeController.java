package com.ga.adab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HomeController {

	@GetMapping("/")
	public String hello() {
		return "Hello , this is our very first java wep appliction";
	}
}
