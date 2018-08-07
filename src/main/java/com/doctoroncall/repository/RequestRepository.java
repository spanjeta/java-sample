package com.doctoroncall.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

	Page<Request> findByTitleLike(String string, Pageable pageable);

}
