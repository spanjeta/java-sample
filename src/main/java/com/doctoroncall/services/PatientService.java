package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.Patient;
import com.doctoroncall.repository.PatientRepository;

@Service
public class PatientService {


	@Autowired
	private PatientRepository patientRepository;
	
	
	
	public  Patient findOne(String name) {
		return findOne(name);
	}


	public void save( Patient  Patient) {
		patientRepository.save( Patient);
		
	}
	
	public Iterable<Patient> findAll() {
		
		return patientRepository.findAll();
	}
	
	
	
	
}
