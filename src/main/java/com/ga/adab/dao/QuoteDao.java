package com.ga.adab.dao;

import org.springframework.data.repository.CrudRepository;
import com.ga.adab.model.Quote;

public interface QuoteDao extends CrudRepository<Quote, Integer>{
public Quote findById(int id);

}
