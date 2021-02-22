package com.ga.adab.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ga.adab.model.Quote;

public interface QuoteDao extends CrudRepository<Quote, Integer>{
public Quote findById(int id);



@Modifying
@Query( value="UPDATE adab.quote SET qreivew =:likes WHERE id =:id", nativeQuery  = true)
public	int setlikes(@Param("id") int id, int likes);
}
