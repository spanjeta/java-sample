package com.doctoroncall.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Appointment;
import com.doctoroncall.entities.User;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByDoctor(User doctor);

	List<Appointment> findByPatient(User patient);

	Appointment findByStateId(Integer stateId);

	List<Appointment> findAllByStateIdAndPatient(Integer stateId, User patient);

	Page<Appointment> findAllByStateIdAndDoctor(Integer stateId, User doctor, Pageable pageable);

	List<Appointment> findByDate(Date date);

	List<Appointment> findAllByDateLessThanEqualAndDateGreaterThanEqual(Date startDate, Date endDate);

	Iterable<Appointment> findAllByDateAndDoctorAndStateId(Date date, User doctor, Integer stateId);

	Appointment findFirstByDoctor(User user);

	Page<Appointment> findByDoctor(String name, Pageable pageable);

}
