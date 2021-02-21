package com.ga.adab.model;

public class JwtResponse {
 
	private String jwt;
	
	public JwtResponse(String jwt) {
		this.jwt = jwt;
	}
	public String getToken() {
		return this.jwt;
	}
 
}
