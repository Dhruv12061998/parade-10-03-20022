package com.uhcl.parade.rest;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.uhcl.parade.dto.FileResponse;
import com.uhcl.parade.dto.ParadeResponse;
import com.uhcl.parade.dto.PradeArticleFilter;
import com.uhcl.parade.model.ParadeArticle;
import com.uhcl.parade.model.ParadeTags;
import com.uhcl.parade.model.ParadeUser;
import com.uhcl.parade.repo.ParadeArticleRepo;
import com.uhcl.parade.service.IFileSytemStorage;

@RestController
@CrossOrigin
@RequestMapping("/api/article")
public class ParadeArticleController {

	@Autowired
	ParadeArticleRepo paradeArtRepo;

	@Autowired
	IFileSytemStorage fileSytemStorage;

	@PostMapping("/")
	public ResponseEntity<?> uploadSingleFile(@RequestPart("file") MultipartFile file,
			@RequestPart("data") ParadeArticle article) {

		String systemfilename = (new Date()).getTime() + "." + FilenameUtils.getExtension(file.getOriginalFilename());

		String upfile = fileSytemStorage.saveFile(file, systemfilename);

		article.setFilename(file.getOriginalFilename());
		article.setFilesize(file.getSize());
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/article/download/")
				.path(upfile).toUriString();

		article.setPath(systemfilename);
		System.out.println("article data :" + article);
		article = paradeArtRepo.save(article);
		return ResponseEntity.ok(new ParadeResponse(article));
	}

	@GetMapping("/download/{partid}")
	public ResponseEntity<?> downloadFile(@PathVariable long partid) {
		Optional<ParadeArticle> paradeUserOp = paradeArtRepo.findById(partid);
		if (paradeUserOp.isPresent()) {
			ParadeArticle partArt = paradeUserOp.get();
			Resource resource = fileSytemStorage.loadFile(partArt.getPath());
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + partArt.getFilename() + "\"")
					.body(resource);
		} else {
			return new ResponseEntity<ParadeResponse>(new ParadeResponse(null, HttpStatus.NOT_FOUND),
					HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings("unchecked")
	@GetMapping("")
	public ResponseEntity<?> getAllParadeArticles() {
		return ResponseEntity.ok(new ParadeResponse(paradeArtRepo.findAll()));
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/filter")
	public ResponseEntity<?> getAllParadeArticles(PradeArticleFilter filter){		
		return ResponseEntity.ok(new ParadeResponse(paradeArtRepo.findByTagsTagAndCreatedAtAfterAndCreatedAtBefore(filter.getTag(),filter.getArtFrom(), filter.getArtTo())));
	}

	@SuppressWarnings("unchecked")
	@GetMapping("puser/{id}")
	public ResponseEntity<?> getParadeArticlesByUserId(@PathVariable long id) {
		List<ParadeArticle> articles = paradeArtRepo.findByOwnerUserid(id);
		return ResponseEntity.ok(new ParadeResponse(articles));

	}

	@SuppressWarnings("unchecked")
	@GetMapping("/{id}")
	public ResponseEntity<?> getParadeArticleById(@PathVariable long id) {

		Optional<ParadeArticle> paradeUserOp = paradeArtRepo.findById(id);
		if (paradeUserOp.isPresent()) {
			return ResponseEntity.ok(new ParadeResponse(paradeUserOp.get()));
		} else {
			return new ResponseEntity<ParadeResponse>(new ParadeResponse(null, HttpStatus.NOT_FOUND),
					HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/tags")
	public ResponseEntity<?> getAllTags() {
		return ResponseEntity.ok(new ParadeResponse(paradeArtRepo.findAllDistinctTags()));

	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/tag/{tag}")
	public ResponseEntity<?> getAllArticlesByTags(@PathVariable String tag) {
		return ResponseEntity.ok(new ParadeResponse(paradeArtRepo.findByTagsTag(tag)));
		//2czQRC@38$

	}

	@GetMapping("/samparadeart")
	public ResponseEntity<?> sampleParadeArticle() {

		Set<ParadeTags> tags = new HashSet<ParadeTags>();
		tags.add(new ParadeTags("Science"));
		tags.add(new ParadeTags("Technology"));
		ParadeArticle art = new ParadeArticle();
		art.setTags(tags);
		return ResponseEntity.ok(new ParadeResponse(art));	
		
	}

}

















