package com.doctoroncall.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.doctoroncall.base.BaseEntity;



@Entity
public class Ticket extends BaseEntity{

	@Size(min = 5,max = 250)
	@NotNull
	private String title;
	
	@NotNull
	private String description;
	
	@OneToOne
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Ticket(User user, String title, String description) {
		super();
		this.user = user;
		this.title = title;
		this.description = description;
	}

	public Ticket() {
		super();
	}

	@Override
	public String toString() {
		return "Ticket [title=" + title + ", description=" + description + ", user=" + user + "]";
	}

	




	

	
	

}
