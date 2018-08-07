package com.doctoroncall.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.doctoroncall.base.BaseEntity;

@Entity
public class Specialization extends BaseEntity{

	@OneToOne
	private Doctor doctorId;
	
	private String speciality;

	public Doctor getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Doctor doctorId) {
		this.doctorId = doctorId;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Specialization() {
		
	}

	public Specialization(String speciality) {
		super();
		this.speciality = speciality;
	}
	
	
}
