package com.doctoroncall.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;

@Entity
public class CallLog extends BaseEntity {

	public static  String[] STATES = { "Connecting", "Active", "Ended" };

	
	private Integer callMinutes = 0;

	@NotEmpty
	@Column(length = 2048)
	private String token;

	@NotEmpty
	@Column(length = 2048)
	private String session;

	@OneToOne
	private User patient;

	@OneToOne
	private User doctor;

	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}
	

	public Integer getCallMinutes() {
		return callMinutes;
	}

	public void setCallMinutes(Integer callMinutes) {
		this.callMinutes = callMinutes;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public CallLog(String token, String session) {
		super();

		this.token = token;
		this.session = session;
	}

	public CallLog() {

	}

}
