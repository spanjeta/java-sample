package com.doctoroncall.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;

@Entity
public class Request extends BaseEntity{
	
	@NotEmpty
	private String title;
	
	private String description;
	
	@OneToOne
	private User doctor;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public Request() {
		
	}

	public Request(String title, String description, User doctor) {
		super();
		this.title = title;
		this.description = description;
		this.doctor = doctor;
	}
	
	
	

}
