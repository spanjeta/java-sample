package com.doctoroncall.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;

@Entity
public class AuthCode extends BaseEntity {

	@Column(length = 1024)
	@NotEmpty
	private String token;
	@Column(length = 2048)
	@NotEmpty
	private String deviceId;

	@ManyToOne
	private User user;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AuthCode(User user, String token, String deviceId) {
		super();
		this.user = user;
		this.token = token;
		this.deviceId = deviceId;
	}

	public AuthCode() {
		super();
	}

}
