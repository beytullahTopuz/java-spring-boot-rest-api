package com.t4zb.cvr.controllers;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.t4zb.cvr.ApiError;
import com.t4zb.cvr.entities.User;
import com.t4zb.cvr.repos.UserRepository;

@CrossOrigin
@RestController
public class AuthController {
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/api/auth/user")
	Object handleAuthenticationUser(@RequestHeader(name="Authorization", required = false) String authorization) {
		log.info(authorization);
		if(authorization == null) {
			ApiError apiError = new ApiError(401, "Unauthorized request", "/api/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
		}
		
		String encoded = authorization.split("Basic ")[1];
		String decoded = new String(Base64.getDecoder().decode(encoded));
		String[] values = decoded.split(":");
		String username = values[0];
		String password = values[1];
	
		
		User dB_user = userRepository.findByUseremail(username);
		
		if(dB_user == null) {
			log.info("User not found");
			ApiError apiError = new ApiError(401, "User not found", "/api/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
		}
		if(!dB_user.getUsertype().equals("standart_user")) {
			log.info("not authorized to login");
			
			ApiError apiError = new ApiError(401, "not authorized to login", "/api/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
		}
		if(!dB_user.getUserpassword().equals(password)) {
			log.info("wrong password");
			
			ApiError apiError = new ApiError(401, "Wrong password", "/api/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
		}
		log.info("success");
		return ResponseEntity.ok().build();	
	}
	
	@PostMapping("/api/auth/admin")
	Object handleAuthenticationAdmin(@RequestHeader(name="Authorization", required = false) String authorization) {
		log.info(authorization);
		if(authorization == null) {
			ApiError apiError = new ApiError(401, "Unauthorized request", "/api/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
		}
		
		String encoded = authorization.split("Basic ")[1];
		String decoded = new String(Base64.getDecoder().decode(encoded));
		String[] values = decoded.split(":");
		String username = values[0];
		String password = values[1];
		
		
		User dB_user = userRepository.findByUseremail(username);
		if(dB_user == null) {
			log.info("User not found");
			ApiError apiError = new ApiError(401, "User not found", "/api/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
		}
		if(!dB_user.getUsertype().equals("admin_user")) {
			log.info("not authorized to login");
			
			ApiError apiError = new ApiError(401, "not authorized to login", "/api/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
		}
		if(!dB_user.getUserpassword().equals(password)) {
			log.info("wrong password");
			
			ApiError apiError = new ApiError(401, "Wrong password", "/api/auth");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
		}
		log.info("success");
		return ResponseEntity.ok().build();	
	}


}
