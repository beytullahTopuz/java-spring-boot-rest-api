package com.t4zb.cvr.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.t4zb.cvr.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	User findByUseremail(String useremail);
	
}
