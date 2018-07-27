package com.doctoroncall.entities;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class DeviceDetail {

	@Id
	@GeneratedValue
	private Integer id; 
	
	private String deviceId;
	
	private String contactNo;
	
	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

	public DeviceDetail() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public DeviceDetail(String deviceId, String contactNo, Date createTime) {
		super();
		this.deviceId = deviceId;
		this.contactNo = contactNo;
		this.createTime = createTime;
	}
	
	public DeviceDetail(String deviceId, String contactNo) {
		super();
		this.deviceId = deviceId;
		this.contactNo = contactNo;
		
	}
	
}
