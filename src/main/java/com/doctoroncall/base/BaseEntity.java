package com.doctoroncall.base;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class BaseEntity {

	public static Integer STATE_INACTIVE = 0;
	public static Integer STATE_ACTIVE = 1;
	public static Integer STATE_DELETED = 2;
	public static String[] STATES = { "Inactive", "Active", "Deleted" };
	public static String[] BEDGES = { "label label-default", "label label-success", "label label-Danger" };

	@Id
	@GeneratedValue
	private Long id;

	@CreatedBy
	private Long createdBy = 1L;

	protected Integer stateId = STATE_ACTIVE;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date createdOn = new Date();

	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date updatedOn = new Date();

	public int getStateId() {
		return stateId;
	}

	public String getState() {

		return STATES[stateId];
	}

	@JsonIgnore
	public String getStateBedge() {

		return "<span class='" + BEDGES[stateId] + "' > " + STATES[stateId] + "</span>";
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@PreUpdate
	public void preUpdate() {
		updatedOn = new Date();
		System.out.println("Listening  Pre Update : " + this);
	}

	@PostPersist
	public void postPersist() {
		System.out.println("Listening  Post Persist : " + this);
	}
}
