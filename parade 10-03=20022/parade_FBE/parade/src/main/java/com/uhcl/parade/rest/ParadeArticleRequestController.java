package com.uhcl.parade.rest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.uhcl.parade.dto.ParadeResponse;
import com.uhcl.parade.model.ParadeArticle;
import com.uhcl.parade.model.ParadeArticleRequest;
import com.uhcl.parade.model.ParadeRequestStatus;
import com.uhcl.parade.model.ParadeUser;
import com.uhcl.parade.repo.ParadeArticleRepo;
import com.uhcl.parade.repo.ParadeArticleRequestRepo;
import com.uhcl.parade.service.IFileSytemStorage;


@RestController
@CrossOrigin
@RequestMapping("/api/paerreq")
public class ParadeArticleRequestController {
	
	

	@Autowired
	ParadeArticleRequestRepo paradeArtReqRepo;
	
	 
	 @PostMapping("")
     public ResponseEntity<?> raiseRequest(@RequestBody ParadeArticleRequest request) {
		 
		 request = paradeArtReqRepo.save(request);
		 return ResponseEntity.ok(request);
		 
     }
	 
	 @PutMapping("")
     public ResponseEntity<?> updateRequest(@RequestBody ParadeArticleRequest request) {
		 request = paradeArtReqRepo.save(request);
		 return ResponseEntity.ok(request);	 		 
     }
	 
	
	@SuppressWarnings("unchecked")
	@GetMapping("")
	public ResponseEntity<?> getAllParadeArticleRequests() {
		return ResponseEntity.ok(new ParadeResponse(paradeArtReqRepo.findAll()));
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/puser/{id}")
	public ResponseEntity<?> getParadeArticleRequestByUserId(@PathVariable long id) {
		List<ParadeArticleRequest> requests = paradeArtReqRepo.findByArticleOwnerUserid(id);
		return ResponseEntity.ok(new ParadeResponse(requests));	
	}
	
	 
	@SuppressWarnings("unchecked")
	@GetMapping("/puser/request/{id}")
	public ResponseEntity<?> getParadeArticleRequestByRequsterId(@PathVariable long id) {
		List<ParadeArticleRequest> requests = paradeArtReqRepo.findByRequestedUserUserid(id);
		return ResponseEntity.ok(new ParadeResponse(requests));	
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/{id}")
	public ResponseEntity<?> getParadeArticleRequestById(@PathVariable long id) {
		
		Optional<ParadeArticleRequest> paradeArtReqOp = paradeArtReqRepo.findById(id);
		if(paradeArtReqOp.isPresent()){
			return ResponseEntity.ok(new ParadeResponse(paradeArtReqOp.get()));
		}else{
			return new ResponseEntity<ParadeResponse>(new ParadeResponse(null,HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);

		}	
		
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/{id}/{status}")
	public ResponseEntity<?> getParadeArticleRequestById(@PathVariable long id,@PathVariable ParadeRequestStatus status) {
		
		Optional<ParadeArticleRequest> paradeArtReqOp = paradeArtReqRepo.findById(id);
		if(paradeArtReqOp.isPresent()){			
			ParadeArticleRequest part = paradeArtReqOp.get();			
			part.setRequestStatus(status);
			part= paradeArtReqRepo.save(part);
			return ResponseEntity.ok(new ParadeResponse(part));
		}else{
			return new ResponseEntity<ParadeResponse>(new ParadeResponse(null,HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);

		}	
		
	}
	
	
	@GetMapping("/samreq")
	public ResponseEntity<?> sampleUser( ) {
		return ResponseEntity.ok(new ParadeResponse(new ParadeArticleRequest()));	
	}
}
