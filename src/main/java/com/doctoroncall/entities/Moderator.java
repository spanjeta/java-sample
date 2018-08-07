package com.doctoroncall.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.doctoroncall.base.BaseEntity;

@Entity
public class Moderator extends BaseEntity{

	private String performance;
	
	
	
	@OneToOne
	private User user;

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Moderator(String performance, User user) {
		super();
		this.performance = performance;
		this.user = user;
	}

	public Moderator() {
		
	}

	

	
	
	
	
}
