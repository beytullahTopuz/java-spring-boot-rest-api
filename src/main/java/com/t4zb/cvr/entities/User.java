package com.t4zb.cvr.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
	
	@Id
	@GeneratedValue
	Long id;
	
	String username;
	String usersurname;
	String userpassword;
	String usertype;
	String useremail;
}
