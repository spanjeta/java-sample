package com.doctoroncall.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.doctoroncall.base.BaseEntity;

@Entity
public class Question extends BaseEntity{

	private String question;
	 
	@OneToOne
	private Activity activity;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivityId(Activity activity) {
		this.activity = activity;
	}

	public Question() {
		
	}

	public Question(String question) {
		super();
		this.question = question;
	}
	
	

}
