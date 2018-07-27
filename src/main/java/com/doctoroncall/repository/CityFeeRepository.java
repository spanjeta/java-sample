package com.doctoroncall.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.CityFee;

public interface CityFeeRepository extends JpaRepository<CityFee, Long> {

	CityFee findById(Long id);

	Page<CityFee> findByCityLike(String name, Pageable pageable);

}
