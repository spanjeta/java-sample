package com.doctoroncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Activity;
import com.doctoroncall.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	Iterable<Question> findAllByActivity(Activity activity);

}
