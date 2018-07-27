package com.doctoroncall.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;

@Entity
public class Patient extends BaseEntity {


	@NotEmpty
	private String idProofType;

	@NotEmpty
	private String idProofNumber;

	@NotEmpty
	private String idProofFile;


	private String nameOfAttendant;

	private String referringDoctor;

	@OneToOne
	private User user;


	public String getIdProofType() {
		return idProofType;
	}

	public void setIdProofType(String idProofType) {
		this.idProofType = idProofType;
	}

	public String getIdProofNumber() {
		return idProofNumber;
	}

	public void setIdProofNumber(String idProofNumber) {
		this.idProofNumber = idProofNumber;
	}

	public String getIdProofFile() {
		return idProofFile;
	}

	public void setIdProofFile(String idProofFile) {
		this.idProofFile = idProofFile;
	}

	public String getNameOfAttendant() {
		return nameOfAttendant;
	}

	public void setNameOfAttendant(String nameOfAttendant) {
		this.nameOfAttendant = nameOfAttendant;
	}

	public Patient() {

	}

	public String getReferringDoctor() {
		return referringDoctor;
	}

	public void setReferringDoctor(String referringDoctor) {
		this.referringDoctor = referringDoctor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Patient(User user, String idProofType, String idProofNumber, String idProofFile, String nameOfAttendant,
			String referringDoctor) {
		super();
		this.idProofType = idProofType;
		this.idProofNumber = idProofNumber;
		this.idProofFile = idProofFile;
		this.nameOfAttendant = nameOfAttendant;
		this.referringDoctor = referringDoctor;
		this.user = user;
	}

	public Patient(User user, String idProofType, String idProofNumber, String idProofFile, String nameOfAttendant) {
		super();
		this.idProofType = idProofType;
		this.idProofNumber = idProofNumber;
		this.idProofFile = idProofFile;
		this.nameOfAttendant = nameOfAttendant;
		this.user = user;
	}

}
