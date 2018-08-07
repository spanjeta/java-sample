package com.doctoroncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
