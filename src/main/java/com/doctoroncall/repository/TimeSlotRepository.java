package com.doctoroncall.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.TimeSlot;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

//	TimeSlot findFirstByDateAndUser(Date date, User doctor);

	Iterable<TimeSlot> findByDate(Date date);

	Page<TimeSlot> findByDoctorLike(String name, Pageable pageable);

}
