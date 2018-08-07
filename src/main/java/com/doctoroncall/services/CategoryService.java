package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.Category;
import com.doctoroncall.entities.Role;
import com.doctoroncall.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	public Category findOne(Long id) {
		return categoryRepository.findOne(id);
	}

	public void save(Category category) {
		categoryRepository.save(category);
		
	}
	
	public Iterable<Category> findAll() {
		
		return categoryRepository.findAll();
	}


	public Page<Category> findAll(String name, Pageable pageable) {

		return categoryRepository.findByTitleLike("%" + name + "%",pageable);
	}

	public void deletecategory(long id) {
		// TODO Auto-generated method stub
		categoryRepository.delete(id);
	}
	

	

	
	
}
