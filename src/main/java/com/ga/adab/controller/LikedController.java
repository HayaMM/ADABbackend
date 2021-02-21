package com.ga.adab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ga.adab.dao.LikedDao;
import com.ga.adab.model.Liked;

@RestController
public class LikedController {

	@Autowired
	private LikedDao dao;
	
	@PostMapping("/liked/add")
	public Liked addLiked(@RequestBody Liked liked) {
		dao.save(liked);
		return liked;
	}
	@GetMapping("/liked/index")
	public Iterable<Liked> getLiked(){
		var it=dao.findAll();
		return it;
	}
}
