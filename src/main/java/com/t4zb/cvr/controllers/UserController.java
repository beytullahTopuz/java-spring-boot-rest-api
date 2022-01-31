package com.t4zb.cvr.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.t4zb.cvr.ApiError;
import com.t4zb.cvr.GenericResponse;
import com.t4zb.cvr.entities.User;
import com.t4zb.cvr.repos.UserRepository;

@CrossOrigin
@RestController()
@RequestMapping("api/users")
public class UserController {
	
	private UserRepository userRepository;
	
	//private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	


	
	@PostMapping
	public Object craterUser(@RequestBody User newUser) {
		
		ApiError error = new ApiError(400, "validation error", "api/users");
		Map<String, String> validationError = new HashMap<>();
		
		String name = newUser.getUsername();
		String surname = newUser.getUseremail();
		String email = newUser.getUseremail();
		String password = newUser.getUserpassword();
		
		newUser.setUsertype("standart_user");
		
		if(name == null || name == "") {
			validationError.put("name", "Name cannot be null");
		}
		if(surname == null || surname == "") {
			validationError.put("surname", "Surname cannot be null");
		}
		if(email == null || email == "") {
			validationError.put("email", "Email cannot be null");
		}
		if(password == null || password == "") {
			validationError.put("password", "Password cannot be null");
		}
		
		if(validationError.size() > 0) {
			error.setValidationError(validationError);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
		 userRepository.save(newUser);
		 return ResponseEntity.ok(new GenericResponse("user created"));
	}
	

	@GetMapping("/{userId}")
	public Object getOneUser(@PathVariable Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}else {
			 return new GenericResponse("user not found");
		}
	}
	
	@PutMapping("/{userId}")
	public Object updateUser(@PathVariable Long userId, @RequestBody User newUser) {
		
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User foundUser = user.get();
			foundUser.setUsername(newUser.getUsername());
			foundUser.setUsersurname(newUser.getUsersurname());
			userRepository.save(foundUser);
			return foundUser;
		}else {
			 return new GenericResponse("user not found");
		}
	}
	

	

}
