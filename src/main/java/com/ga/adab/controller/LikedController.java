package com.ga.adab.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ga.adab.dao.LikedDao;
import com.ga.adab.dao.QuoteDao;
import com.ga.adab.dao.UserDao;
import com.ga.adab.model.Liked;
import com.ga.adab.model.Quote;
import com.ga.adab.model.User;

@RestController
public class LikedController {

	@Autowired
	private LikedDao dao;
	
	@Autowired
	private QuoteDao quotedao;
	
	@Autowired
	private UserDao userdao;
	
	@Transactional
	@PostMapping("/liked/add")
	public boolean addLiked(@RequestBody ObjectNode json)throws IOException  {
		
		System.out.println("---------------in like adddd");
		try {
			
		String userid = json.get("user").asText();
		int qouteid = json.get("qouteid").asInt();
		boolean like = json.get("like").asBoolean();
		System.out.println("---------------in like "+userid+" "+qouteid+" "+like);

		Quote idq = quotedao.findById(qouteid);
		User idu = userdao.findByEmailAddress(userid);
		Liked likes = new Liked();
		likes.setQuote(idq);
		likes.setUser(idu);
		likes.setQliked(like);
		
		dao.save(likes);
		
		int likess = dao.getlikes(qouteid);
		quotedao.setlikes(qouteid, likess);
		return true;

		}catch (NullPointerException ex) {
			System.out.println( "Caught this exception " + ex);
	          ex.printStackTrace();
	  		return false;

	      }

	}
	@GetMapping("/liked/index")
	public Iterable<Liked> getLiked(){
		var it=dao.findAll();
		return it;
	}

	@GetMapping("/liked/detail")
	public boolean DetailLiked(@RequestParam int id,@RequestBody ObjectNode json) {
		int userid = json.get("user").asInt();
		int qouteid = json.get("qouteid").asInt();
		Liked liked=dao.findById(id);
		if(liked.getUser().equals(userdao.findById(userid)) && liked.getQuote().equals(quotedao.findById(qouteid))) {
		return liked.getQliked();
		}
		
		return false;
	}
	@PutMapping("/liked/edit")
	public Liked editLiked(@RequestBody Liked liked) {
		dao.save(liked);
		return liked;
	}
	@DeleteMapping("/liked/delete")
	public boolean deleteLiked(@RequestParam int id) {
		dao.deleteById(id);
		return true;
	}

}
