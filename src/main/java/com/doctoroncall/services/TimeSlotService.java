package com.doctoroncall.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.TimeSlot;
import com.doctoroncall.repository.TimeSlotRepository;

@Service
public class TimeSlotService {

	@Autowired
	private TimeSlotRepository timeSlotRepository;

	public TimeSlot findOne(Long id) {
		return timeSlotRepository.findOne(id);
	}

	public TimeSlot save(TimeSlot timeSlot) {
		return timeSlotRepository.save(timeSlot);

	}

	public Iterable<TimeSlot> findAll() {

		return timeSlotRepository.findAll();
	}


	public Iterable<TimeSlot> findByDate(Date date) {
		// TODO Auto-generated method stub
		return timeSlotRepository.findByDate(date);
	}

	public Page<TimeSlot> findAll(String name, Pageable pageable) {
		return timeSlotRepository.findByDoctorLike("%" + name + "%",pageable);
	}
}
