package com.ga.adab.dao;

import org.springframework.data.repository.CrudRepository;
import com.ga.adab.model.Liked;

public interface LikedDao extends CrudRepository<Liked, Integer>{
public Liked findById(int id);
}
