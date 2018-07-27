package com.doctoroncall.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;

@Entity
public class FileSession extends BaseEntity{
	
	@NotEmpty
	private String title;
	
	private String diagonisis;
	 
	private String neededSession;
	
	private String description;
	
	private Integer days;
	
	@OneToOne
	private User doctor;
	
	@OneToOne
	private User patient;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiagonisis() {
		return diagonisis;
	}

	public void setDiagonisis(String diagonisis) {
		this.diagonisis = diagonisis;
	}

	public String getNeededSession() {
		return neededSession;
	}

	public void setNeededSession(String neededSession) {
		this.neededSession = neededSession;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
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

	public FileSession(String title, String diagonisis, String neededSession, String description, Integer days,
			User doctor, User patient) {
		super();
		this.title = title;
		this.diagonisis = diagonisis;
		this.neededSession = neededSession;
		this.description = description;
		this.days = days;
		this.doctor = doctor;
		this.patient = patient;
	}

	public FileSession() {
		super();
	}


	

	
	
	
	

}
