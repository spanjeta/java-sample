package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.Activity;
import com.doctoroncall.repository.ActivityRepository;

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository activityRepository;


	public void save(Activity activity) {
		activityRepository.save(activity);
		
	}
	
	public Iterable<Activity> findAll() {
		
		return activityRepository.findAll();
	}

	public Activity findOne(Long activity) {
		return activityRepository.findOne(activity);
	}

	public Page<Activity> findAll(String name, Pageable pageable) {
		return activityRepository.findByTitleLike("%" + name + "%",pageable);
	}
	
	
	
}
