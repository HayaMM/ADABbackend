package com.ga.adab.service;



import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import com.ga.adab.dao.UserDao;
import com.ga.adab.model.User;

@Service
public class MyService {

    private static final Logger logger = LoggerFactory.getLogger("MyService.class");
    @Autowired
    private UserDao dao;

    public int saveImage(User user) {
        try {
            dao.save(user);
            return 1;
        } catch (Exception e) {
            logger.error("ERROR", e);
            return 0;
        }
    }

//    public User getImages(Long id) {
//        Optional findById = dao.findById(id);
//        if (findById.isPresent()) {
//            User getImageDetails = (User) findById.get();
//            logger.info("id= " + getImageDetails.getId() + " FirstName= " + getImageDetails.getFirstName());
//            return getImageDetails;
//        } else {
//            return null;
//        }
//    }
}