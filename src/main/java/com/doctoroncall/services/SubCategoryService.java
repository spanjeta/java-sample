package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.Payment;
import com.doctoroncall.entities.SubCategory;
import com.doctoroncall.repository.SubCategoryRepository;

@Service
public class SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	public Payment findOne(String name) {
		return findOne(name);
	}

	public Iterable<SubCategory> findAll() {

		return subCategoryRepository.findAll();
	}

	public void save(SubCategory subcat) {
		// TODO Auto-generated method stub
		subCategoryRepository.save(subcat);
	}

	public SubCategory findOne(Long id) {
		// TODO Auto-generated method stub
		return subCategoryRepository.findOne(id);
	}

	public Page<SubCategory> findAll(String name, Pageable pageable) {
		return subCategoryRepository.findByTitleLike("%" + name + "%", pageable);
	}

	public void deletesubcategory(long id) {
		subCategoryRepository.delete(id);
		
	}

}
