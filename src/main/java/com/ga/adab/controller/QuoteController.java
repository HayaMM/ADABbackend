package com.ga.adab.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ga.adab.dao.QuoteDao;
import com.ga.adab.dao.UserDao;
import com.ga.adab.model.Quote;
import com.ga.adab.model.User;

@RestController
public class QuoteController {
@Autowired
private QuoteDao dao;

@Autowired
private UserDao udao;

@PostMapping("/quote/add")
public Quote addQuote(@RequestBody Quote quote,@RequestParam String User ) {
	User u=udao.findByEmailAddress(User);
	quote.setUser(u);
	dao.save(quote);
	return quote;
}
@GetMapping("/quote/index")
public Iterable<Quote> getQuote(){
	var it=dao.findAll();
	return it;
} 

@GetMapping("/quote/detail")
public Quote quoteDetails(@RequestParam int id) {
Quote quote =dao.findById(id);
return quote;
}
@PutMapping("/quote/edit")
public Quote editQuote(@RequestBody Quote quote) {
dao.save(quote);
return quote;
}
@DeleteMapping("/quote/delete")
public boolean deleteQuote(@RequestParam int id) {
	//Quote quote=dao.findById(id);
	dao.deleteById(id);
	return true;
}
}
