package com.doctoroncall.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.doctoroncall.base.BaseEntity;

@Entity
public class Activity extends BaseEntity {

	@NotEmpty
	private String title;

	@OneToOne
	private Category category;

	@OneToOne
	private SubCategory subcategory;

	@OneToOne
	private Disorder disorder;

	private Integer toughnessLevel;

	private Integer type;

	private Integer solution;

	public static String[] ACTIVITY_TYPES = { " Only Text", "With Audio Clip", "With One Image " ,"With Image Full Screen","With One Image","With One Image Solution","With One Full Screen Image As Solution"};
	public static String[] SOLUTIONS = { "Two Buttons", "Audio", "Text Area" ,"Select One Image From 2 Or 3 Or 4","Select One Full Screen Image From Multiple"};
	public static String[] TOUGHNESS = {"Level 1","Level 2","Level3"};
	
	@OneToOne
	private User doctor;

	@OneToOne
	private User patient;

	
	public Integer getToughnessLevel() {
		return toughnessLevel;
	}

	public void setToughnessLevel(Integer toughnessLevel) {
		this.toughnessLevel = toughnessLevel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSolution() {
		return solution;
	}

	public void setSolution(Integer solution) {
		this.solution = solution;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(SubCategory subcategory) {
		this.subcategory = subcategory;
	}

	public Disorder getDisorder() {
		return disorder;
	}

	public void setDisorder(Disorder disorder) {
		this.disorder = disorder;
	}

	
	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}
	

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	

	public static String[] getSOLUTIONS() {
		return SOLUTIONS;
	}

	public static void setSOLUTIONS(String[] sOLUTIONS) {
		SOLUTIONS = sOLUTIONS;
	}

	public Activity(String title, Category category, SubCategory subcategory, Disorder disorder, Integer toughnessLevel,
			Integer type, Integer solution, User doctor, User patient) {
		super();
		this.title = title;
		this.category = category;
		this.subcategory = subcategory;
		this.disorder = disorder;
		this.toughnessLevel = toughnessLevel;
		this.type = type;
		this.solution = solution;
		this.doctor = doctor;
		this.patient = patient;
	}

	public Activity(String title, Category category, User doctor, User patient) {
		super();
		this.title = title;
		this.category = category;
		this.doctor = doctor;
		this.patient = patient;
	}

	public Activity() {

	}

	public Activity(String title, Category category, SubCategory subcategory) {
		super();
		this.title = title;
		this.category = category;
		this.subcategory = subcategory;
	}

	public Activity(String title, Category category, SubCategory subcategory, Disorder disorder) {
		super();
		this.title = title;
		this.category = category;
		this.subcategory = subcategory;
		this.disorder = disorder;
	}
	

}
