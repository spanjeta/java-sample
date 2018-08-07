package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.Request;
import com.doctoroncall.repository.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository requestRepository;

	public Request findOne(Long id) {
		return requestRepository.findOne(id);
	}

	public void save(Request request) {
		requestRepository.save(request);

	}

	public Iterable<Request> findAll() {

		return requestRepository.findAll();
	}

	public Page<Request> findAlll(String name, Pageable pageable) {
		return requestRepository.findByTitleLike("%" + name + "%", pageable);
	}

}
