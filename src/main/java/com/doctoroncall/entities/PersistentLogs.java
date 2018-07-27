package com.doctoroncall.entities;

import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

public class PersistentLogs {
	@NotEmpty
	String username;
	@NotEmpty
	@Id
	String series;
	@NotEmpty
	String token;
	@NotEmpty
	String timestamp;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public PersistentLogs(String username, String series, String token, String timestamp) {
		super();
		this.username = username;
		this.series = series;
		this.token = token;
		this.timestamp = timestamp;
	}
	public PersistentLogs() {
		
	}
	
}
