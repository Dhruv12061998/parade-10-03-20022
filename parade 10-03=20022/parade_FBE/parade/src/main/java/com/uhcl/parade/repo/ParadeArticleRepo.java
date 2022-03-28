package com.uhcl.parade.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uhcl.parade.model.ParadeArticle;

public interface ParadeArticleRepo extends JpaRepository<ParadeArticle, Long>{
	
	List<ParadeArticle> findByOwnerUserid(long userid);
	
	@Query(value = "SELECT distinct(tag) FROM parade_article_tags;", nativeQuery = true)
	List<String> findAllDistinctTags();
	
	List<ParadeArticle> findByTagsTag(String tag);
	
	List<ParadeArticle> findByTagsTagAndCreatedAtAfterAndCreatedAtBefore(String tag,Date createdAtFrom, Date createdAtTo);
	

}
