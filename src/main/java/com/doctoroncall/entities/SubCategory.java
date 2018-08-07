package com.doctoroncall.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;

@Entity
public class SubCategory extends BaseEntity {
	@NotEmpty
	private String title;

	@OneToOne
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public SubCategory() {

	}

	public SubCategory(String title) {
		super();
		this.title = title;
	}

}
