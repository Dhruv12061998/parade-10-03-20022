package com.uhcl.parade.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileSytemStorage {
	void init();

	String saveFile(MultipartFile file, String systemfilename);

	Resource loadFile(String fileName);
}