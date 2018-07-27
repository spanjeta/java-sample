package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.Doctor;
import com.doctoroncall.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
	
	
	
	public Doctor findOne(Long id) {
		return findOne(id);
	}


	public void save(Doctor doctor) {
		doctorRepository.save(doctor);
		
	}
	
	public Iterable<Doctor> findAll() {
		
		return doctorRepository.findAll();
	}
	
	
	
	
}
