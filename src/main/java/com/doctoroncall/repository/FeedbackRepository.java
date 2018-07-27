package com.doctoroncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>{

}
