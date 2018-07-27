package com.doctoroncall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.Disorder;
import com.doctoroncall.repository.DisorderRepository;

@Service
public class DisorderService {

	@Autowired
	private DisorderRepository disorderRepository;

	public Disorder findOne(Long id) {
		return disorderRepository.findOne(id);
	}

	public Iterable<Disorder> findAll() {

		return disorderRepository.findAll();
	}

	public void save(Disorder disorder) {
		disorderRepository.save(disorder);
	}

	public Page<Disorder> findAll(String name, Pageable pageable) {
		return disorderRepository.findByTitleLike("%" + name + "%", pageable);
	}

	public void deletedisorder(long id) {
		// TODO Auto-generated method stub
		disorderRepository.delete(id);
	}
}
