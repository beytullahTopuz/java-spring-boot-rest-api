package com.t4zb.cvr.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="cv")
@Data
public class Cv {

	@Id
	@GeneratedValue
	Long id;
	
	String cv_name;
	String cv_surname;
	String cv_number;
	String cv_email;
	@ElementCollection
	List<String> cv_education;
	@ElementCollection
	List<String> cv_work_experience;
	@ElementCollection
	List<String> cv_professional_experience;
}
