package com.doctoroncall.entities;

import javax.persistence.Entity;

import com.doctoroncall.base.BaseEntity;

@Entity
public class Answer extends BaseEntity{

	private String answer;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Answer() {
		
	}

	public Answer(String answer) {
		super();
		this.answer = answer;
	}
	
	

}
