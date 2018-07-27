package com.doctoroncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Appointment;
import com.doctoroncall.entities.FileSession;

public interface FileSessionRepository extends JpaRepository<Appointment, Long> {

	void save(FileSession fileSession);

}
