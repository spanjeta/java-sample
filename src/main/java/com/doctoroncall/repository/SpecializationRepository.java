package com.doctoroncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long>{

}
