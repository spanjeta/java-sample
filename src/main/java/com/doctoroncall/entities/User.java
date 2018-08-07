package com.doctoroncall.entities;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator;

@Entity
public class User extends BaseEntity {

	public static final int STATE_INACTIVE = 0;
	public static final int STATE_ACTIVE = 1;
	public static final int STATE_BLOCKED = 2;
	public static final String[] STATES = { "Inactive", "Active", "Blocked" };

	@Email
	@NotEmpty
	@Column(unique = true)
	private String email;

	@OneToOne
	private Role role;

	@NotEmpty
	private String name;

	@NotEmpty
	private String contactNumber;

	@JsonIgnore
	@Size(min = 4)
	private String password;

	private String gender = "Male";

	private String dob = null;

	private String pincode = null;

	private String nameOfGardian = null;

	private String relation = null;

	private String address = null;
	private Integer performance;

	public User(String email, String name, String gender, String dob, String pincode, String nameOfGardian,
			String relation, String address, String contactNumber, String password, List<Appointment> appointments,
			Role role, String password_repeat, boolean agree, String confirmationToken) {
		super();
		this.email = email;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.pincode = pincode;
		this.nameOfGardian = nameOfGardian;
		this.relation = relation;
		this.address = address;
		this.contactNumber = contactNumber;
		this.password = password;
		this.appointments = appointments;
		this.role = role;
		this.password_repeat = password_repeat;
		this.agree = agree;
		this.confirmationToken = confirmationToken;
	}

	public Integer getPerformance() {
		return performance;
	}

	public void setPerformance(Integer performance) {
		this.performance = performance;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private List<Appointment> appointments;

	// private

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	private String password_repeat;

	private boolean agree;

	private String confirmationToken;

	public String getPassword_repeat() {
		return password_repeat;
	}

	public void setPassword_repeat(String password_repeat) {
		this.password_repeat = password_repeat;
	}

	public boolean isAgree() {
		return agree;
	}

	public void setAgree(boolean agree) {
		this.agree = agree;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getNameOfGardian() {
		return nameOfGardian;
	}

	public void setNameOfGardian(String nameOfGardian) {
		this.nameOfGardian = nameOfGardian;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String email, String name, String password, String contactNumber) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.contactNumber = contactNumber;

	}

	public User(String email, String name, String password, String contactNumber, Integer performance) {
		super();
		this.email = email;
		this.name = name;

		this.password = password;
		this.contactNumber = contactNumber;
		this.performance = performance;
	}

	public User(String email, String name, String password, int stateId, String gender, String dob, String pincode,
			String nameOfGardian, String relation, String address, List<Appointment> appointments, Role role) {
		super();
		this.email = email;
		this.name = name;
		this.stateId = stateId;
		this.gender = gender;
		this.dob = dob;
		this.pincode = pincode;
		this.nameOfGardian = nameOfGardian;
		this.relation = relation;
		this.address = address;
		this.password = password;
		// this.appointments = appointments;
		this.role = role;
	}

	public User() {

	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken() {
		this.confirmationToken = UUID.randomUUID().toString();
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Boolean isActive() {
		// TODO Auto-generated method stub
		return stateId == STATE_ACTIVE;
	}

	public Boolean isAdmin() {

		Role roleUser = getRole();
		if (roleUser != null && roleUser.sameAs("ADMIN")) {
			return true;
		}

		return false;
	}

	public Boolean isModerator() {
		Role roleUser = getRole();
		if (roleUser != null && roleUser.sameAs("MODERATOR")) {
			return true;
		}

		return false;
	}

	public Boolean isDoctor() {

		Role roleUser = getRole();
		if (roleUser != null && roleUser.sameAs("DOCTOR")) {
			return true;
		}

		return false;
	}
	public Boolean isPatient() {

		Role roleUser = getRole();
		if (roleUser != null && roleUser.sameAs("PATIENT")) {
			return true;
		}

		return false;
	}
}
