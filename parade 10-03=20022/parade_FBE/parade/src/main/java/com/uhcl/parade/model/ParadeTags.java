package com.uhcl.parade.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Embeddable
public class ParadeTags {
	
	
	String tag;
	
	@CreationTimestamp
	@JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
	Date createdAt;
	
	@Column
	long artCount =0;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public long getArtCount() {
		return artCount;
	}

	public void setArtCount(long artCount) {
		this.artCount = artCount;
	}

	public ParadeTags(String tag) {
		super();
		this.tag = tag;
	}

	public ParadeTags() {
		super();
	}
	
	
	
	
	

}
