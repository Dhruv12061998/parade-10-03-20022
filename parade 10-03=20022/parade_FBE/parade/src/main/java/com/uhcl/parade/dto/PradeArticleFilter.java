package com.uhcl.parade.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PradeArticleFilter {
	 
	String tag;
	
	@JsonFormat(pattern="MM-dd-yyyy")
	Date artFrom;
	
	@JsonFormat(pattern="MM-dd-yyyy")
	Date artTo;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Date getArtFrom() {
		return artFrom;
	}

	public void setArtFrom(Date artFrom) {
		this.artFrom = artFrom;
	}

	public Date getArtTo() {
		return artTo;
	}

	public void setArtTo(Date artTo) {
		this.artTo = artTo;
	}

	public PradeArticleFilter(String tag, Date artFrom, Date artTo) {
		super();
		this.tag = tag;
		this.artFrom = artFrom;
		this.artTo = artTo;
	}

	public PradeArticleFilter() {
		super();
	}
	
	
}
