package com.doctoroncall.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.Activity;
import com.doctoroncall.entities.Question;
import com.doctoroncall.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public Iterable<Question> findAllByActivity(Activity activity) {

		return questionRepository.findAllByActivity(activity);
	}

	public void save(Question question) {
		questionRepository.save(question);

	}

	public Iterable<Question> findAll() {

		return questionRepository.findAll();
	}

}
