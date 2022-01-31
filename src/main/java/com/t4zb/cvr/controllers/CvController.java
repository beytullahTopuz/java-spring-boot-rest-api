package com.t4zb.cvr.controllers;

import java.util.List;

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

import java.util.Optional;

import com.t4zb.cvr.GenericResponse;
import com.t4zb.cvr.entities.Cv;
import com.t4zb.cvr.repos.CvRepository;

@CrossOrigin
@RestController()
@RequestMapping("api/cv")
public class CvController {

	private CvRepository cvRepository;
	
	public CvController(CvRepository cvRepository) {
		this.cvRepository = cvRepository;
	}
	
	@GetMapping
	public List<Cv> getAllCv(){
		return cvRepository.findAll();
	}
	
	@GetMapping("/{cvId}")
	 public Object getOneCV(@PathVariable Long cvId) {
		
		 Optional<Cv> cv = cvRepository.findById(cvId);
		 
		 if(cv.isPresent()) {
			 return cv.get();
		 }else {
			 return ResponseEntity.notFound();
	
		 }
			
	}
	
	@PostMapping
	public GenericResponse craterCv(@RequestBody Cv newCv) {
		
		newCv.setId(newCv.getId());
		cvRepository.save(newCv);
		
		return new GenericResponse("cv created");
	}
	
	@PutMapping("/{cvId}")
	public GenericResponse updateCv(@PathVariable Long cvId, @RequestBody Cv newCv) {
		
		Optional<Cv> cv = cvRepository.findById(cvId);
		if(cv.isPresent()) {
			Cv foundCv = cv.get();
			foundCv.setCv_name(newCv.getCv_name());
			foundCv.setCv_surname(newCv.getCv_surname());
			foundCv.setCv_number(newCv.getCv_number());
			foundCv.setCv_email(newCv.getCv_email());
			foundCv.setCv_education(newCv.getCv_education());
			foundCv.setCv_work_experience(newCv.getCv_work_experience());
			foundCv.setCv_professional_experience(newCv.getCv_professional_experience());
			
			cvRepository.save(foundCv);
			return new GenericResponse("cv updated");
		}
		else {
		
			return new GenericResponse("cv not found");
		}
	}
	
}
