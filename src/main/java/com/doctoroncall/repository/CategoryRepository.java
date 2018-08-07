package com.doctoroncall.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Page<Category> findByTitleLike(String name, Pageable pageable);


}
