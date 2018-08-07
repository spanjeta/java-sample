package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.Payment;
import com.doctoroncall.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	
	
	public Payment findOne(String name) {
		return findOne(name);
	}


	public void save(Payment payment) {
		paymentRepository.save(payment);
		
	}
	
	public Iterable<Payment> findAll() {
		
		return paymentRepository.findAll();
	}
	
}
