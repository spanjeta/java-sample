package com.doctoroncall.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.Appointment;
import com.doctoroncall.entities.User;
import com.doctoroncall.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	public void add(Appointment appointment) {
		appointmentRepository.save(appointment);

	}

	public Appointment findOne(Long id) {
		return appointmentRepository.findOne(id);
	}

	public void save(Appointment appointment) {
		appointmentRepository.save(appointment);

	}

	public Iterable<Appointment> findAll() {
		return appointmentRepository.findAll();
	}


	public long count() {
		return appointmentRepository.count();
	}

	public  Page<Appointment>  findAllByDoctor(User doctor,Pageable pageable) {
		return appointmentRepository.findAllByStateIdAndDoctor(Appointment.STATE_ACTIVE, doctor,pageable);
	}

	public Iterable<Appointment> findByDateAndDoctorAndStateId(Date date,User doctor) {
		return appointmentRepository.findAllByDateAndDoctorAndStateId(date, doctor,Appointment.STATE_ACTIVE);
	}

	public Appointment findNext(User user) {
		return appointmentRepository.findFirstByDoctor(user);
	}

	

	public Page<Appointment> findAll(String name, Pageable pageable) {
		return appointmentRepository.findByDoctor("%" + name + "%",pageable);
	}




}
