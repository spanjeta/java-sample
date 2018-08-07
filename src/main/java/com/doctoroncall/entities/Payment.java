package com.doctoroncall.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.doctoroncall.base.BaseEntity;

@Entity
public class Payment extends BaseEntity{
	
	private Integer costRupees;
	
	@OneToOne
	private User patient;

	public Integer getCostRupees() {
		return costRupees;
	}

	public void setCostRupees(Integer costRupees) {
		this.costRupees = costRupees;
	}

	public Payment(Integer costRupees, User patient) {
		super();
		this.costRupees = costRupees;
		this.patient = patient;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	public Payment() {
		
	}

	
	
	

}
