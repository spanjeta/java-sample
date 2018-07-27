package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.Feedback;
import com.doctoroncall.repository.FeedbackRepository;

@Service
public class FeedbackService {

	
	

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	
	
	public Feedback findOne(Long id) {
		return findOne(id);
	}


	public void save(Feedback feedback) {
		feedbackRepository.save(feedback);
		
	}
	
	public Iterable<Feedback> findAll() {
		
		return feedbackRepository.findAll();
	}
}
