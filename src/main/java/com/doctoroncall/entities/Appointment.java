package com.doctoroncall.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;

@Entity
public class Appointment extends BaseEntity {

	@OneToOne
	private User doctor;

	@OneToOne
	private User patient;

	private Boolean paidStatus;

	private Boolean emergencyStatus;


	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}


	public Boolean getPaidStatus() {
		return paidStatus;
	}

	public void setPaidStatus(Boolean paidStatus) {
		this.paidStatus = paidStatus;
	}

	public Boolean getEmergencyStatus() {
		return emergencyStatus;
	}

	public void setEmergencyStatus(Boolean emergencyStatus) {
		this.emergencyStatus = emergencyStatus;
	}

	public Appointment() {
		super();
	}

	public Appointment(User doctor, User patient, Date date) {
		super();
		this.doctor = doctor;
		this.patient = patient;
		this.date = date;
		emergencyStatus = false;
		paidStatus = false;
		stateId = STATE_ACTIVE;
	}



}
