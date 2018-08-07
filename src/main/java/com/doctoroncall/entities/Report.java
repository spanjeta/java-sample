package com.doctoroncall.entities;



import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;

@Entity
public class Report extends BaseEntity{

	@NotEmpty
	private String description;
	
	@OneToOne
	private Patient patientId;
	
	@OneToOne
	private Doctor doctorId;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Patient getPatientId() {
		return patientId;
	}

	public void setPatientId(Patient patientId) {
		this.patientId = patientId;
	}

	public Doctor getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Doctor doctorId) {
		this.doctorId = doctorId;
	}

	public Report() {
		
		
	}

	public Report(String description) {
		super();
		this.description = description;
	}
	
	
	
	
}
