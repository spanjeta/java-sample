package com.doctoroncall.entities;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;

@Entity
public class CityFee extends BaseEntity {

	@NotEmpty
	private String city;

	private String pincode;

	private String fee;

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getCity() {
		return city;
	}

	public String getPrice() {
		return fee;
	}

	public void setPrice(String price) {
		this.fee = price;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public CityFee(String city, String pincode, String fee) {
		super();
		this.city = city;
		this.pincode = pincode;
		this.fee = fee;
	}

	public CityFee() {

	}

}
