package com.doctoroncall.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

	Page<Activity> findByTitleLike(String name, Pageable pageable);

}
