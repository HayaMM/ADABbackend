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
		
		try {
			
		String userid = json.get("user").asText();
		int qouteid = json.get("qouteid").asInt();
		boolean like = json.get("like").asBoolean();

		Quote idq = quotedao.findById(qouteid);
		User idu = userdao.findByEmailAddress(userid);
		Liked likes = new Liked();
		likes.setQuote(idq);
		likes.setUser(idu);
		likes.setQliked(like);
		
		dao.save(likes);
		
		int likess = dao.getlikes(qouteid);
		System.out.println("---------------in like "+likess);

		quotedao.setlikes(qouteid, likess);
		return true;

		}catch (NullPointerException ex) {
			System.out.println( "Caught this exception " + ex);
	          ex.printStackTrace();
	  		return false;

	      }

	}

	@Transactional
	@GetMapping("/liked/islike")
	public boolean isLiked(@RequestParam("qouteid") int qouteid, @RequestParam("useremail") String useremail) {
		System.out.println("-----------");

		boolean islike =false;
		 try {
		User isuser = userdao.findByEmailAddress(useremail);
		Quote isqoute = quotedao.findById(qouteid);
		if((isuser != null) && (isqoute != null)) {
			int uid = isuser.getId();
			 islike = dao.islike(qouteid, uid);
		}
		return islike;
    } catch (Exception e) {
        System.out.println("ERROR"+" "+ e);
        return islike;
    }
		
		
	}
	@Transactional
	@DeleteMapping("/liked/delete")
	public boolean deleteLiked(@RequestParam("qid") int qid, @RequestParam("useremail") String useremail) {
		User isuser = userdao.findByEmailAddress(useremail);
		int uid = isuser.getId();
		Quote idq = quotedao.findById(qid);
		if(!isuser.equals(null) && !idq.equals(null)) {
		int id = dao.isDeleteid(qid,uid);
		dao.deleteById(id);
		int likess = dao.getlikes(qid);
		quotedao.setlikes(qid, likess);
		return true;
		}else {
		return false;
		}
	}

}
