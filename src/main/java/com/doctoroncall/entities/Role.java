package com.doctoroncall.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private List<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Role(String name, List<User> users) {
		this.name = name;
		this.users = users;
	}

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public Boolean sameAs(String anotherString) {
		return name.compareTo(anotherString) == 0;
	}
}
