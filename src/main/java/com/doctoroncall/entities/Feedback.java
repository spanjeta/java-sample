package com.doctoroncall.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;

@Entity
public class Feedback extends BaseEntity {

	@NotEmpty
	private String message;
	
	@OneToOne
	private Doctor doctorId;
	
	@OneToOne
	private Patient patientId;

	public Feedback() {
		
	}

	public Feedback(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Doctor getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Doctor doctorId) {
		this.doctorId = doctorId;
	}

	public Patient getPatientId() {
		return patientId;
	}

	public void setPatientId(Patient patientId) {
		this.patientId = patientId;
	}
	
	
}
