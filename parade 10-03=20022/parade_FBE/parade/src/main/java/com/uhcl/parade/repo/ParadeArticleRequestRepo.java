package com.uhcl.parade.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uhcl.parade.model.ParadeArticleRequest;

public interface ParadeArticleRequestRepo extends JpaRepository<ParadeArticleRequest, Long> {
	
	List<ParadeArticleRequest> findByArticlePartId(long partid);
	
	List<ParadeArticleRequest> findByArticleOwnerUserid(long id);
	
	List<ParadeArticleRequest> findByRequestedUserUserid(long id);
	
	List<ParadeArticleRequest> findByArticlePartIdAndRequestedUser(long partId, long userid);
	
}
