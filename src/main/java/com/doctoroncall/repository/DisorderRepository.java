package com.doctoroncall.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Disorder;

public interface DisorderRepository extends JpaRepository<Disorder,Long> {

	Page<Disorder> findByTitleLike(String name, Pageable pageable);

}
