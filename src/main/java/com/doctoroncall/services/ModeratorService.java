package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.Moderator;
import com.doctoroncall.repository.ModeratorRepository;

@Service
public class ModeratorService {


	@Autowired
	private ModeratorRepository moderatorRepository;
	
	
	
	public Moderator findOne(Long id) {
		return findOne(id);
	}


	public void save(Moderator moderator) {
		moderatorRepository.save(moderator);
		
	}
	
	public Iterable<Moderator> findAll() {
		
		return moderatorRepository.findAll();
	}
	
}
