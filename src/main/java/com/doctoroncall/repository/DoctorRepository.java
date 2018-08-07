package com.doctoroncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {



	

}
