package com.uhcl.parade.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ParadeArticle {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long partId;
	
	@ManyToOne
	ParadeUser owner;
	
	
	@CreationTimestamp
	@JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
	Date createdAt;
	
	@UpdateTimestamp
	@JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
	Date updatedAt;
	
	
	@ElementCollection
	Set<ParadeTags> tags;

	
	String path;
	
	String description;
	
	String title;
	
	String readtime;
	
	long filesize;
	
	String filename;
	

	public long getPartId() {
		return partId;
	}


	public void setPartId(long partId) {
		this.partId = partId;
	}


	public ParadeUser getOwner() {
		return owner;
	}


	public void setOwner(ParadeUser owner) {
		this.owner = owner;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public Set<ParadeTags> getTags() {
		return tags;
	}


	public void setTags(Set<ParadeTags> tags) {
		this.tags = tags;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getReadtime() {
		return readtime;
	}


	public void setReadtime(String readtime) {
		this.readtime = readtime;
	}


	public long getFilesize() {
		return filesize;
	}


	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	@Override
	public String toString() {
		return "ParadeArticle [partId=" + partId + ", owner=" + owner + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", tags=" + tags + ", path=" + path + ", description=" + description + ", title=" + title
				+ ", readtime=" + readtime + ", filesize=" + filesize + ", filename=" + filename + "]";
	}
	
	
	
		
}
