package com.uhcl.parade.dto;

public class FileResponse {
	
	private String fileName;
    private String fileUrl;
    private String message;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public FileResponse(String fileName, String fileUrl, String message) {
		super();
		this.fileName = fileName;
		this.fileUrl = fileUrl;
		this.message = message;
	}
	public FileResponse() {
		super();
	}
    
    

}
