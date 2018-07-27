package com.doctoroncall.entities;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;

@Entity
public class Category extends BaseEntity{
	
	@NotEmpty
	private String title;



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	public Category() {
		
	}


	public Category(String title) {
		super();
		this.title = title;
	}
	
	
	
	
	

}
