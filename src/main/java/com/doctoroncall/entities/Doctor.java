package com.doctoroncall.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;

@Entity
public class Doctor extends BaseEntity {

	@NotEmpty
	private String experiance;

	private String specialization;
	@NotNull
	@OneToOne
	private User user;

	@NotEmpty
	private String description;

	private String performance;

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getExperiance() {
		return experiance;
	}

	public void setExperiance(String experiance) {
		this.experiance = experiance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public Doctor() {

	}

	public Doctor(String experiance, String description, String password, String performance) {
		super();
		this.experiance = experiance;
		this.description = description;
		this.performance = performance;
	}

	public Doctor(User user, String experiance, String description) {
		super();
		this.user = user;
		this.experiance = experiance;
		this.description = description;
	}

	public Doctor(String experiance, String specialization, User user, String description, String performance) {
		super();
		this.experiance = experiance;
		this.specialization = specialization;
		this.user = user;
		this.description = description;
		this.performance = performance;
	}

}
