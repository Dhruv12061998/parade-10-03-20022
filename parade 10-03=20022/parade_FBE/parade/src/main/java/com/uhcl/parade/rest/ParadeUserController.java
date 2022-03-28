package com.uhcl.parade.rest;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uhcl.parade.dto.LoginDto;
import com.uhcl.parade.dto.ParadeResponse;
import com.uhcl.parade.model.ParadeUser;
import com.uhcl.parade.repo.ParadeUserRepo;
import com.uhcl.parade.utils.RestUtils;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ParadeUserController {

	@Autowired
	ParadeUserRepo userRepo;

	private static final Logger logger = LoggerFactory.getLogger(ParadeUserController.class);

	public boolean logRequest(String name) {
		logger.info("Api request received for " + name);
		return false;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody LoginDto login) {

		System.out.println("Date For login:" + login.toString());
		ParadeUser user = userRepo.findByEmailAndPassword(login.getUsername(), RestUtils.md5(login.getPassword()));
		System.out.println("User:" + login.getUsername() + ",Ps:" + login.getPassword());
		if (user != null) {
			logRequest("Login request for usename: " + login.getUsername() + " and pswd: " + login.getPassword()
					+ " results are found");
			String token = null;
			while (token == null) {
				token = RestUtils.getActivationKey();
				ParadeUser tUser = userRepo.findByToken(token);
				if (tUser != null) {
					token = null;
				}
			}
			user.setToken(token);
			user.setTokenGeneratedAt();
			userRepo.save(user);
			return ResponseEntity.ok(new ParadeResponse(user, HttpStatus.OK, "Login Successfull"));

		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ParadeResponse(null, HttpStatus.UNAUTHORIZED, null,"invalid Credintials"));

		}
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody ParadeUser newUser) {

		logRequest("Date For Registration:" + newUser.toString());
		ParadeUser user = userRepo.findByEmail(newUser.getEmail());
		if (user != null) {
			logRequest("Registration request for usename: " + newUser.getEmail() + " already exists");
		return new ResponseEntity<ParadeResponse>(new ParadeResponse(null,HttpStatus.NOT_ACCEPTABLE,"","User Already Exists"),HttpStatus.EXPECTATION_FAILED);

		} else {			
			try{
				newUser.setPassword(RestUtils.md5(newUser.getPassword()));
				newUser = userRepo.save(newUser);
				return ResponseEntity.ok(new ParadeResponse(newUser));				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new ResponseEntity<ParadeResponse>(new ParadeResponse(e,HttpStatus.EXPECTATION_FAILED,"",e.getLocalizedMessage()),HttpStatus.EXPECTATION_FAILED);
			}	
		}
	}
		
	@SuppressWarnings("unchecked")
	@PutMapping("/puser")
	public ResponseEntity<?> updateParadeUser(@RequestBody ParadeUser newUser) {
		newUser = userRepo.save(newUser);
		return ResponseEntity.ok(new ParadeResponse(newUser));
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/puser")
	public ResponseEntity<?> getAllParadeUsers() {
		return ResponseEntity.ok(new ParadeResponse(userRepo.findAll()));
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/puser/{id}")
	public ResponseEntity<?> getParadeUserById(@PathVariable long id) {
		
		Optional<ParadeUser> paradeUserOp = userRepo.findById(id);
		if(paradeUserOp.isPresent()){
			return ResponseEntity.ok(new ParadeResponse(paradeUserOp.get()));
		}else{
			return new ResponseEntity<ParadeResponse>(new ParadeResponse(null,HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);

		}
		
		
	}
	
	@GetMapping("/samuser")
	public ResponseEntity<?> sampleUser( ) {
		return ResponseEntity.ok(new ParadeResponse(new ParadeUser()));	
	}

}
