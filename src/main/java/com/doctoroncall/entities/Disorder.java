package com.doctoroncall.entities;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;

@Entity
public class Disorder extends BaseEntity {

	@NotEmpty
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Disorder() {

	}

	public Disorder(String disoder) {
		super();
		this.title = disoder;
	}

}
