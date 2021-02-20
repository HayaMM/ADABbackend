package com.ga.adab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ga.adab.dao.QuoteDao;
import com.ga.adab.model.Quote;

@RestController
public class QuoteController {
@Autowired
private QuoteDao dao;
@PostMapping("/quote/add")
public Quote addQuote(@RequestBody Quote quote) {
	dao.save(quote);
	return quote;
}
}
