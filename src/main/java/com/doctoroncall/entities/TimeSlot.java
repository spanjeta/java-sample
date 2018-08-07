package com.doctoroncall.entities;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.doctoroncall.base.BaseEntity;

@Entity
public class TimeSlot extends BaseEntity {

	public static String[] STATES = { "Unavailble", "Availble", "Deleted" };
	public static String[] BEDGES = { "label label-default", "label label-success", "label label-Danger" };

	@OneToOne
	private User doctor;

	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	private Boolean slot_9am = false;
	private Boolean slot_10am = false;
	private Boolean slot_11am = false;
	private Boolean slot_12pm = false;
	private Boolean slot_1pm = false;
	private Boolean slot_2pm = false;
	private Boolean slot_3pm = false;
	private Boolean slot_4pm = false;
	private Boolean slot_5pm = false;
	private Boolean slot_6pm = false;
	private Boolean slot_7pm = false;
	private Boolean slot_8pm = false;

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public TimeSlot() {

	}

	public TimeSlot(Date date, User doctor) {
		super();
		this.doctor = doctor;
		this.date = date;
	}

	@Override
	public String toString() {
		return "TimeSlot [doctor=" + doctor + ", date=" + date + ", slot_9am=" + slot_9am + ", slot_10am=" + slot_10am
				+ ", slot_11am=" + slot_11am + ", slot_12pm=" + slot_12pm + ", slot_1pm=" + slot_1pm + ", slot_2pm="
				+ slot_2pm + ", slot_3pm=" + slot_3pm + ", slot_4pm=" + slot_4pm + ", slot_5pm=" + slot_5pm
				+ ", slot_6pm=" + slot_6pm + ", slot_7pm=" + slot_7pm + ", slot_8pm=" + slot_8pm + "]";
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getSlot_9am() {
		return slot_9am;
	}

	public void setSlot_9am(Boolean slot_9am) {
		this.slot_9am = slot_9am;
	}

	public Boolean getSlot_10am() {
		return slot_10am;
	}

	public void setSlot_10am(Boolean slot_10am) {
		this.slot_10am = slot_10am;
	}

	public Boolean getSlot_11am() {
		return slot_11am;
	}

	public void setSlot_11am(Boolean slot_11am) {
		this.slot_11am = slot_11am;
	}

	public Boolean getSlot_12pm() {
		return slot_12pm;
	}

	public void setSlot_12pm(Boolean slot_12pm) {
		this.slot_12pm = slot_12pm;
	}

	public Boolean getSlot_1pm() {
		return slot_1pm;
	}

	public void setSlot_1pm(Boolean slot_1pm) {
		this.slot_1pm = slot_1pm;
	}

	public Boolean getSlot_2pm() {
		return slot_2pm;
	}

	public void setSlot_2pm(Boolean slot_2pm) {
		this.slot_2pm = slot_2pm;
	}

	public Boolean getSlot_3pm() {
		return slot_3pm;
	}

	public void setSlot_3pm(Boolean slot_3pm) {
		this.slot_3pm = slot_3pm;
	}

	public Boolean getSlot_4pm() {
		return slot_4pm;
	}

	public void setSlot_4pm(Boolean slot_4pm) {
		this.slot_4pm = slot_4pm;
	}

	public Boolean getSlot_5pm() {
		return slot_5pm;
	}

	public void setSlot_5pm(Boolean slot_5pm) {
		this.slot_5pm = slot_5pm;
	}

	public Boolean getSlot_6pm() {
		return slot_6pm;
	}

	public void setSlot_6pm(Boolean slot_6pm) {
		this.slot_6pm = slot_6pm;
	}

	public Boolean getSlot_7pm() {
		return slot_7pm;
	}

	public void setSlot_7pm(Boolean slot_7pm) {
		this.slot_7pm = slot_7pm;
	}

	public Boolean getSlot_8pm() {
		return slot_8pm;
	}

	public void setSlot_8pm(Boolean slot_8pm) {
		this.slot_8pm = slot_8pm;
	}

}
