package com.doctoroncall.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {

	Page<SubCategory> findByTitleLike(String name, Pageable pageable);

	
}
