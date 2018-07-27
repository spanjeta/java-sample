package com.doctoroncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

}
