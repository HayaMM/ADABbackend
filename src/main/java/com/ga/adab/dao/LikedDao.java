package com.ga.adab.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ga.adab.model.Liked;

public interface LikedDao extends CrudRepository<Liked, Integer> {
	// query to get the average rate from the review to the same game
	@Query(value = "SELECT COUNT(qliked) FROM adab.liked WHERE fk_quote_id =:id\r\n" + "", nativeQuery = true)
	public int getlikes(@Param("id") int id);

	 
	@Query(value = "select qliked from adab.liked where fk_quote_id =:idq and fk_user_id =:idu" + "", nativeQuery = true)
	public boolean islike(@Param("idq") int idq,@Param("idu") int idu);
	public Liked findById(int id);
	
	@Query(value = "select id from adab.liked where fk_quote_id =:idq and fk_user_id =:idu" + "", nativeQuery = true)
	public int isDeleteid(@Param("idq") int idq,@Param("idu") int idu);
}
