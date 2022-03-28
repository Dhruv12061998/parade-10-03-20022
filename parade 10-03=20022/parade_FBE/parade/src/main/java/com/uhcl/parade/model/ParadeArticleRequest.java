package com.uhcl.parade.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class ParadeArticleRequest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long parId;
		
	@ManyToOne
	private ParadeArticle article;
	
	@ManyToOne
	private ParadeUser requestedUser;
		
	private ParadeRequestStatus requestStatus;
	
	@CreationTimestamp
	private Date requestedAt;
	
	@UpdateTimestamp
	private Date updatedAt;
	
	private String comment;

	public long getParId() {
		return parId;
	}

	public void setParId(long parId) {
		this.parId = parId;
	}

	public ParadeArticle getArticle() {
		return article;
	}

	public void setArticle(ParadeArticle article) {
		this.article = article;
	}

	

	public ParadeUser getRequestedUser() {
		return requestedUser;
	}

	public void setRequestedUser(ParadeUser requestedUser) {
		this.requestedUser = requestedUser;
	}

	public ParadeRequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(ParadeRequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Date getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(Date requestedAt) {
		this.requestedAt = requestedAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
		

}
