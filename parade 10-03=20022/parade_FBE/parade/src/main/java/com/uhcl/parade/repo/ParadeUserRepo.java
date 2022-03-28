package com.uhcl.parade.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uhcl.parade.model.ParadeUser;


public interface ParadeUserRepo extends JpaRepository<ParadeUser, Long>{
	
//	ParadeUser find

	ParadeUser findByEmailAndPassword(String username,String password);

	ParadeUser findByToken(String token);

	ParadeUser findByEmail(String username);
	
}
