package com.doctoroncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Report;

public interface ReportRepository extends JpaRepository<Report,Long> {

}
